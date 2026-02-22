package com.nawid.EMS.entity;

import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "employee_attendance",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "date"})
        }
)

public class EmployeeAttendance extends BaseEntity {

    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private Boolean isPresent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public EmployeeAttendance(LocalDate date, LocalTime checkIn, LocalTime checkOut, Boolean isPresent, Employee employee) {
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isPresent = isPresent;
        this.employee = employee;
    }

    public EmployeeAttendance() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
