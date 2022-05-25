package com.example.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity

@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_name")
    private String name;
    @Column(name = "day_in")
    private Date dayIn;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "RoleID")
    private Role role;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "employee_language",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages = new HashSet<>();

    public Set<ProjectManage> getProjectManages() {
        return projectManages;
    }

    public void setProjectManages(Set<ProjectManage> projectManages) {
        this.projectManages = projectManages;
    }

    @OneToMany(mappedBy = "employee")
    private Set<ProjectManage> projectManages = new LinkedHashSet<>();

    public Employee() {
    }

    public Employee(int id, String name, Date dayIn, Status status, Role role, Set<Language> languages) {
        this.id = id;
        this.name = name;
        this.dayIn = dayIn;
        this.status = status;
        this.role = role;
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDayIn() {
        return dayIn;
    }

    public String formatDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dayIn);
    }

    public void setDayIn(Date dayIn) {
        this.dayIn = dayIn;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }


}
