package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.settings.ProjectFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    public void saveOrUpdateProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public Project findById(Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            return new Project();
        }
        return optionalProject.get();
    }

    @Override
    public Page<Project> findPaginated(int pageNo, int pageSize, ProjectFilterRequest projectFilterRequest) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.projectRepository.findAllByProject(projectFilterRequest, pageable);

    }

}
