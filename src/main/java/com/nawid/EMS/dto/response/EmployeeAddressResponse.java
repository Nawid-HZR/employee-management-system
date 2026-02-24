package com.nawid.EMS.dto.response;


public class EmployeeAddressResponse {

    private Long id;
    private Long employeeId;
    private String employeeName;

    private String street;
    private String city;
    private String country;
    private Boolean isPrimary;

    public EmployeeAddressResponse(Long id, Long employeeId, String employeeName, String street, String city, String country, Boolean isPrimary) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.street = street;
        this.city = city;
        this.country = country;
        this.isPrimary = isPrimary;
    }

    public EmployeeAddressResponse() {
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
}