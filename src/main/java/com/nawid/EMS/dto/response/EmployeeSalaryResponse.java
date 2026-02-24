package com.nawid.EMS.dto.response;



import java.time.LocalDate;

public class EmployeeSalaryResponse {

    private Long id;
    private Long employeeId;
    private String employeeName;
    private Double basicSalary;
    private Double allowance;
    private Double totalSalary;
    private LocalDate effectiveFrom;
    private Boolean isActive;

    public EmployeeSalaryResponse(Long id, Long employeeId, String employeeName, Double basicSalary, Double allowance, Double totalSalary, LocalDate effectiveFrom, Boolean isActive) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.totalSalary = totalSalary;
        this.effectiveFrom = effectiveFrom;
        this.isActive = isActive;
    }

    public EmployeeSalaryResponse() {
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

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}