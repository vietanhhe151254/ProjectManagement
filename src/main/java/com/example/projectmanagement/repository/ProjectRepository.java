package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query(value = "SELECT p FROM Project p order by p.id DESC ")
    Page<Project> findAllByProject(Pageable pageable);
}
