package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.settings.EmployeeFilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> listAll();
    List<Employee> listEmplOfProject(Project project);

    void saveOrUpdateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Optional<Employee> findById(Integer id);



    Page<Employee> findPaginated(int pageNo, int pageSize, EmployeeFilterRequest filterRequest);
}
