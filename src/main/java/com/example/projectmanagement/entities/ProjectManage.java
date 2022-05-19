package com.example.projectmanagement.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "projectmanage")
public class ProjectManage {

    @EmbeddedId
    ManageProjectKey id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id",nullable = false)
    Project project;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id",nullable = false)
    Employee employee;
    @ManyToOne
    @JoinColumn(name = "status_id",nullable = false)
    private Status status;
    @Column(name = "is_delete",nullable = false)
    Boolean isDelete;

    @Transient
    List<Employee> employeeList;

    public ManageProjectKey getId() {
        return id;
    }

    public void setId(ManageProjectKey id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
