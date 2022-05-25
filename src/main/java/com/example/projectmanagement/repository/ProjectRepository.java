package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.settings.ProjectFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query(value = "select distinct project from Project project left join project.languages l " +
            " where (coalesce(:#{#filter.keyword}, '') = '' or project.projectName LIKE CONCAT('%',:#{#filter.keyword},'%'))\n" +
            " and (coalesce(:#{#filter.status}, 0) = 0 or project.status.id = :#{#filter.status}) \n" +
            " and (coalesce(:#{#filter.language}, 0) = 0 or l.id = :#{#filter.language}) \n" +
            " and ((coalesce(:#{#filter.startDate}, '') = '' or :#{#filter.startDate} <= project.startDate) " +
            " and (coalesce(:#{#filter.endDate}, '') = '' or :#{#filter.endDate} >= project.endDate)) ")
    Page<Project> findAllByProject(@Param("filter") ProjectFilterRequest filterRequest, Pageable pageable);
}
