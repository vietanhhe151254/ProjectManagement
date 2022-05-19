package com.example.projectmanagement.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "data_master")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private String name;
    @OneToMany(mappedBy = "status")
    private Collection<Employee> employees;
    @OneToMany(mappedBy = "status")
    private Collection<Project> projects;
    @OneToMany(mappedBy = "status")
    private Collection<ProjectManage> projectManages;


    public Status() {
    }

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
