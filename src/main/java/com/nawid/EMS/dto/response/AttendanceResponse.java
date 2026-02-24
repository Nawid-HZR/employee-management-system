package com.nawid.EMS.dto.response;



import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponse {

    private Long id;
    private Long employeeId;
    private String employeeName;

    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Boolean isPresent;

    public AttendanceResponse(Long id, Long employeeId, String employeeName, LocalDate date, LocalTime checkIn, LocalTime checkOut, Boolean isPresent) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isPresent = isPresent;
    }

    public AttendanceResponse() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}