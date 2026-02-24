package com.nawid.EMS.dto.response;

import java.time.LocalDateTime;
public class EmployeeDocumentResponse {

    private Long id;
    private String documentName;
    private String filePath;
    private LocalDateTime uploadedAt;

    private Long employeeId;
    private String employeeName;

    public EmployeeDocumentResponse(Long id, String documentName, String filePath, LocalDateTime uploadedAt, Long employeeId, String employeeName) {
        this.id = id;
        this.documentName = documentName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public EmployeeDocumentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}