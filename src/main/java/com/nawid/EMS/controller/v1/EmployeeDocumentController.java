package com.nawid.EMS.controller.v1;

import com.nawid.EMS.dto.response.EmployeeDocumentResponse;
import com.nawid.EMS.security.user.CustomUserPrincipal;
import com.nawid.EMS.service.EmployeeDocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class EmployeeDocumentController {

    private final EmployeeDocumentService documentService;

    public EmployeeDocumentController(EmployeeDocumentService documentService) {
        this.documentService = documentService;
    }

    // =====================================
    // EMPLOYEE ENDPOINTS
    // =====================================

    @PostMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<EmployeeDocumentResponse> uploadMyDocument(
            @RequestParam("documentName") String documentName,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        EmployeeDocumentResponse response =
                documentService.upload(
                        principal.getEmployeeId(),
                        documentName,
                        file);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    // Get my documents
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me")
    public ResponseEntity<List<EmployeeDocumentResponse>> getMyDocuments(
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                documentService.getByEmployee(principal.getEmployeeId()));
    }

    // Download my document
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/me/{id}/download")
    public ResponseEntity<Resource> downloadMyDocument(
            @PathVariable Long id,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        Resource resource =
                documentService.download(id, principal.getEmployeeId());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Delete my document
    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/me/{id}")
    public ResponseEntity<String> deleteMyDocument(
            @PathVariable Long id,
            Authentication authentication) {

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        return ResponseEntity.ok(
                documentService.delete(id, principal.getEmployeeId()));
    }

    // =====================================
    // ADMIN ENDPOINTS
    // =====================================

    // Get documents of specific employee
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeDocumentResponse>> getEmployeeDocuments(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                documentService.getByEmployee(employeeId));
    }

    // Admin download document (must still pass employeeId for ownership validation)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{employeeId}/{documentId}/download")
    public ResponseEntity<Resource> adminDownloadDocument(
            @PathVariable Long employeeId,
            @PathVariable Long documentId) {

        Resource resource =
                documentService.download(documentId, employeeId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Admin delete document
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{employeeId}/{documentId}")
    public ResponseEntity<String> adminDeleteDocument(
            @PathVariable Long employeeId,
            @PathVariable Long documentId) {

        return ResponseEntity.ok(
                documentService.delete(documentId, employeeId));
    }
}