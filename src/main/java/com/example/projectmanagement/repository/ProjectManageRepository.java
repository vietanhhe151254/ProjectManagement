package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.ProjectManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectManageRepository extends JpaRepository<ProjectManage, Integer> {
    @Query(value = "SELECT pm FROM ProjectManage pm where pm.employee.id = ?1")
    List<ProjectManage> findByIdEmployee(Integer id);

    @Query(value = "SELECT pm FROM ProjectManage pm where pm.project.id = ?1")
    List<ProjectManage> findByIdProject(Integer id);
}
