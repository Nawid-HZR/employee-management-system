package com.nawid.EMS.dto.response;



import java.time.LocalDate;

public class EmployeeResponse {

    private Long id;
    private String fullName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;

    private String departmentName;
    private String positionTitle;

    public EmployeeResponse(Long id, String fullName, String email, LocalDate dateOfBirth, LocalDate hireDate, String departmentName, String positionTitle) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.departmentName = departmentName;
        this.positionTitle = positionTitle;
    }

    public EmployeeResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }
}