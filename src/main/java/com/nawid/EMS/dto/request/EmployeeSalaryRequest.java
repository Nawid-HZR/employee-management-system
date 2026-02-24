package com.nawid.EMS.dto.request;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class EmployeeSalaryRequest {

    @NotNull
    private Long employeeId;

    @Positive
    private Double basicSalary;

    @Positive
    private Double allowance;

    @NotNull
    private LocalDate effectiveFrom;

    public EmployeeSalaryRequest(Long employeeId, Double basicSalary, Double allowance, LocalDate effectiveFrom) {
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.effectiveFrom = effectiveFrom;
    }

    public EmployeeSalaryRequest() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
}
