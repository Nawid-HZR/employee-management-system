package com.nawid.EMS.dto.request;



import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @NotNull(message = "Department id is required")
    private Long departmentId;

    @NotNull(message = "Position id is required")
    private Long positionId;

    public EmployeeRequest(String fullName, String email, LocalDate dateOfBirth, LocalDate hireDate, Long departmentId, Long positionId) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.departmentId = departmentId;
        this.positionId = positionId;
    }

    public EmployeeRequest() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}