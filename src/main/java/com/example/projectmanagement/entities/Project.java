package com.example.projectmanagement.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "project_name")
    private String projectName;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @Column(name = "describtion")
    private String describtion;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "project_language",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<ProjectManage> projectManages = new LinkedHashSet<>();

    public Set<ProjectManage> getProjectManages() {
        return projectManages;
    }

    public void setProjectManages(Set<ProjectManage> projectManages) {
        this.projectManages = projectManages;
    }

    public Project() {
    }



    public Project(int id, String projectName, Status status, String describtion, LocalDate startDate, LocalDate endDate, Set<Language> languages) {
        this.id = id;
        this.projectName = projectName;
        this.status = status;
        this.describtion = describtion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.languages = languages;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }


}
