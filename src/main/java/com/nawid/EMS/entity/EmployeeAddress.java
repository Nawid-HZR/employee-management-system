package com.nawid.EMS.entity;

import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_addresses")

public class EmployeeAddress extends BaseEntity {

    private String street;
    private String city;
    private String country;
    private Boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public EmployeeAddress(String street, String city, String country, Boolean isPrimary, Employee employee) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.isPrimary = isPrimary;
        this.employee = employee;
    }

    public EmployeeAddress() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}