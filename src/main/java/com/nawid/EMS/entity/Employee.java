package com.nawid.EMS.entity;

import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")

public class Employee extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate dateOfBirth;
    private LocalDate hireDate;

    // Many Employees → One Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Many Employees → One Position
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    // One Employee → Many Addresses
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeAddress> addresses;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeSalary> salaries;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeDocument> documents;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeAttendance> attendances;

    public Employee(String fullName, String email, LocalDate dateOfBirth, LocalDate hireDate, Department department, Position position, List<EmployeeAddress> addresses, List<EmployeeSalary> salaries, List<EmployeeDocument> documents, List<EmployeeAttendance> attendances) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.hireDate = hireDate;
        this.department = department;
        this.position = position;
        this.addresses = addresses;
        this.salaries = salaries;
        this.documents = documents;
        this.attendances = attendances;
    }

    public Employee() {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<EmployeeAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<EmployeeAddress> addresses) {
        this.addresses = addresses;
    }

    public List<EmployeeSalary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<EmployeeSalary> salaries) {
        this.salaries = salaries;
    }

    public List<EmployeeDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<EmployeeDocument> documents) {
        this.documents = documents;
    }

    public List<EmployeeAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<EmployeeAttendance> attendances) {
        this.attendances = attendances;
    }
}