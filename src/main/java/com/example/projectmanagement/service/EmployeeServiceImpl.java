package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Language;
import com.example.projectmanagement.entities.Project;
import com.example.projectmanagement.entities.ProjectManage;
import com.example.projectmanagement.settings.EmployeeFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.projectmanagement.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Employee> listEmplOfProject( Project project) {
        List<Employee> employeeList = employeeRepository.joinProject();
        List<Employee> result = new ArrayList<>();
        Set<Language> languageSet = project.getLanguages();
        Set<ProjectManage> employees = project.getProjectManages();
        boolean isContainLanguage = false,isExistEmployee = false;
        for (Employee employee: employeeList) {
            for(Language language : employee.getLanguages()){
                if(languageSet.contains(language)){
                    isContainLanguage = true;
                    break;
                }
            }
            for (ProjectManage projectManage : employees){
                if (Objects.equals(projectManage.getEmployee().getId(), employee.getId())) {
                    isExistEmployee = true;
                    break;
                }
            }
            if (isContainLanguage && !isExistEmployee){
                result.add(employee);
            }
            isExistEmployee = false;
            isContainLanguage = false;
        }
        return result;
    }

    @Override
    public void saveOrUpdateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findById(Integer id) {

        return employeeRepository.findById(id);

    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, EmployeeFilterRequest filterRequest) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.employeeRepository.findAllByEmployee(pageable,filterRequest);
    }



}
