package com.nawid.EMS.entity;

import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_documents")

public class EmployeeDocument extends BaseEntity {

    private String documentName;
    private String filePath;
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public EmployeeDocument(String documentName, String filePath, LocalDateTime uploadedAt, Employee employee) {
        this.documentName = documentName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
        this.employee = employee;
    }

    public EmployeeDocument() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}