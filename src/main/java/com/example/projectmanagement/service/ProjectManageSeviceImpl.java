package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.ProjectManage;
import com.example.projectmanagement.repository.ProjectManageRepository;
import com.example.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectManageSeviceImpl implements ProjectManageService {
    @Autowired
    ProjectManageRepository projectManageRepository;

    @Override
    public List<ProjectManage> findByIdEmployee(Integer id) {
        return projectManageRepository.findByIdEmployee(id);
    }

    @Override
    public List<ProjectManage> findByIdProject(Integer id) {
        return projectManageRepository.findByIdProject(id);
    }

    @Override
    public void saveOrUpdateProject(ProjectManage projectManage) {
        projectManageRepository.saveAndFlush(projectManage);
    }

}
