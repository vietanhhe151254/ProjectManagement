package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.settings.EmployeeFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT *,count(pm.employee_id) \n" +
            "from projectmanagement.employee e left join projectmanagement.projectmanage pm on e.ID = pm.employee_id \n" +
            "where e.status_id = 1  and (pm.status_id = 1 Or pm.status_id is null) \n" +
            "group by e.ID having count(pm.employee_id) < 3",nativeQuery = true)
    List<Employee> joinProject();

    @Query(value = "SELECT distinct employee FROM Employee employee left join employee.languages el\n" +
            " where (coalesce(:#{#filter.keyword}, '') = '' or employee.name LIKE CONCAT('%',:#{#filter.keyword},'%'))\n " +
            " and (coalesce(:#{#filter.status}, 0) = 0 or employee.status.id = :#{#filter.status}) \n " +
            " and (coalesce(:#{#filter.role}, 0) = 0 or employee.role.id= :#{#filter.role})\n " +
            " and (coalesce(:#{#filter.language}, 0) = 0 or  el.id = :#{#filter.language})  \n")
    Page<Employee> findAllByEmployee(Pageable pageable, @Param("filter") EmployeeFilterRequest filterRequest);
}
