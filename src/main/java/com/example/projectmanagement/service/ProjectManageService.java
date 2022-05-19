package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.ProjectManage;

import java.util.List;

public interface ProjectManageService{
    List<ProjectManage> findByIdEmployee(Integer id);
    List<ProjectManage> findByIdProject(Integer id);
    void saveOrUpdateProject(ProjectManage projectManage);
}
