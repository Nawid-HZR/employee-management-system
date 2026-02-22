package com.nawid.EMS.entity;

import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee_salaries")
public class EmployeeSalary extends BaseEntity {

    private Double basicSalary;
    private Double allowance;
    private LocalDate effectiveFrom;
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public EmployeeSalary(Double basicSalary, Double allowance, LocalDate effectiveFrom, Boolean isActive, Employee employee) {
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.effectiveFrom = effectiveFrom;
        this.isActive = isActive;
        this.employee = employee;
    }

    public EmployeeSalary() {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}