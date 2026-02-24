package com.nawid.EMS.dto.request;

import jakarta.validation.constraints.NotBlank;

public class EmployeeDocumentRequest {

    @NotBlank
    private String documentName;

    public EmployeeDocumentRequest(String documentName) {
        this.documentName = documentName;
    }

    public EmployeeDocumentRequest() {
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}