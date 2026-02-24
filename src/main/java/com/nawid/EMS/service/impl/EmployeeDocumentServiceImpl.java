package com.nawid.EMS.service.impl;


import com.nawid.EMS.config.FileStorageProperties;
import com.nawid.EMS.dto.response.EmployeeDocumentResponse;
import com.nawid.EMS.entity.Employee;
import com.nawid.EMS.entity.EmployeeDocument;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.EmployeeDocumentRepository;
import com.nawid.EMS.repo.EmployeeRepository;
import com.nawid.EMS.service.EmployeeDocumentService;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeDocumentServiceImpl
        implements EmployeeDocumentService {

    private final EmployeeDocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;
    private final FileStorageProperties fileStorageProperties;
    private final ModelMapper mapper;
    private final Path uploadPath;

    public EmployeeDocumentServiceImpl(
            EmployeeDocumentRepository documentRepository,
            EmployeeRepository employeeRepository,
            FileStorageProperties fileStorageProperties,
            ModelMapper mapper) throws IOException {

        this.documentRepository = documentRepository;
        this.employeeRepository = employeeRepository;
        this.fileStorageProperties = fileStorageProperties;
        this.mapper = mapper;

        this.uploadPath = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath()
                .normalize();

        Files.createDirectories(this.uploadPath);
    }

    @Override
    public EmployeeDocumentResponse upload(Long employeeId,
                                           String documentName,
                                           MultipartFile file) {

        if (file.isEmpty()) {
            throw new BadRequestException("File is empty");
        }

        // Validate type (example: allow PDF + images)
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/pdf")
                        || contentType.startsWith("image/"))) {
            throw new BadRequestException("Only PDF or image files allowed");
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        try {
            // Extract safe extension
            String extension = StringUtils
                    .getFilenameExtension(file.getOriginalFilename());

            String storedFileName = UUID.randomUUID()
                    + (extension != null ? "." + extension : "");

            Path targetLocation = uploadPath.resolve(storedFileName);

            if (!targetLocation.normalize().startsWith(uploadPath)) {
                throw new RuntimeException("Invalid file path");
            }

            Files.copy(file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            EmployeeDocument document = new EmployeeDocument();
            document.setEmployee(employee);
            document.setDocumentName(documentName);
            document.setFilePath(storedFileName);
            document.setUploadedAt(LocalDateTime.now());

            return mapToResponse(documentRepository.save(document));

        } catch (IOException ex) {
            throw new RuntimeException("Could not store file", ex);
        }
    }

    @Override
    public List<EmployeeDocumentResponse> getByEmployee(Long employeeId) {

        return documentRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Resource download(Long documentId, Long employeeId) {

        EmployeeDocument document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        if (!document.getEmployee().getId().equals(employeeId)) {
            throw new BadRequestException("You cannot access this file");
        }

        try {
            Path filePath = uploadPath.resolve(document.getFilePath())
                    .normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new ResourceNotFoundException("File not found");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found", e);
        }
    }

    @Override
    public String delete(Long id, Long employeeId) {

        EmployeeDocument document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found"));

        if (!document.getEmployee().getId().equals(employeeId)) {
            throw new BadRequestException("You cannot delete this file");
        }

        try {
            Files.deleteIfExists(uploadPath.resolve(document.getFilePath()));
        } catch (IOException ignored) {}

        documentRepository.delete(document);

        return "Deleted successfully";
    }

    private EmployeeDocumentResponse mapToResponse(EmployeeDocument doc) {

        EmployeeDocumentResponse response =
                mapper.map(doc, EmployeeDocumentResponse.class);

        response.setEmployeeId(doc.getEmployee().getId());
        response.setEmployeeName(doc.getEmployee().getFullName());
        response.setStoredFileName(doc.getFilePath());

        return response;
    }
}