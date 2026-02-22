package com.nawid.EMS.entity;


import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "departments")

public class Department extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Department(String name, String description, List<Employee> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}