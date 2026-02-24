package com.nawid.EMS.dto.response;

import java.time.LocalDateTime;

public class EmployeeDocumentResponse {

    private Long id;
    private Long employeeId;
    private String employeeName;
    private String documentName;
    private String storedFileName;
    private LocalDateTime uploadedAt;


    public EmployeeDocumentResponse(Long id, Long employeeId, String employeeName, String documentName, String storedFileName, LocalDateTime uploadedAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.documentName = documentName;
        this.storedFileName = storedFileName;
        this.uploadedAt = uploadedAt;
    }

    public EmployeeDocumentResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}