package com.example.projectmanagement.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ManageProjectKey implements Serializable {
    @Column(name = "project_id",nullable = false)
    int projectId;
    @Column(name = "employee_id",nullable = false)
    int employeeId;
}
