package com.nawid.EMS.dto.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeAddressRequest {









    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private Boolean isPrimary;

    public EmployeeAddressRequest( String street, String city, String country, Boolean isPrimary) {

        this.street = street;
        this.city = city;
        this.country = country;
        this.isPrimary = isPrimary;
    }

    public EmployeeAddressRequest() {
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