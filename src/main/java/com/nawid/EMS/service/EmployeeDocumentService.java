package com.nawid.EMS.service;

import com.nawid.EMS.dto.response.EmployeeDocumentResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeDocumentService {

    EmployeeDocumentResponse upload(Long employeeId,
                                    String documentName,
                                    MultipartFile file);

    List<EmployeeDocumentResponse> getByEmployee(Long employeeId);

    Resource download(Long documentId, Long employeeId);

    String delete(Long id, Long employeeId);
}