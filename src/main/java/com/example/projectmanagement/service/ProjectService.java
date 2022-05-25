package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.settings.ProjectFilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    List<Project> listAll();

    void saveOrUpdateProject(Project project);

    void deleteProject(Project project);

    Project findById(Integer id);
    Page<Project> findPaginated(int pageNo, int pageSize, ProjectFilterRequest projectFilterRequest);
}
