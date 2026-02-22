package com.nawid.EMS.entity;


import com.nawid.EMS.common.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "positions")

public class Position extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;
    private String description;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<Employee> employees;

    public Position(String title, String description, List<Employee> employees) {
        this.title = title;
        this.description = description;
        this.employees = employees;
    }

    public Position() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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