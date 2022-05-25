package com.example.projectmanagement.controler;

import com.example.projectmanagement.entities.*;
import com.example.projectmanagement.repository.ProjectManageRepository;
import com.example.projectmanagement.service.*;
import com.example.projectmanagement.settings.EmployeeFilterRequest;
import com.example.projectmanagement.settings.ProjectFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ProjectManageService projectManageService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    ProjectManageRepository projectManageRepository;

    @RequestMapping("")
    public String findPaginated(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap model,
                                @ModelAttribute(name = "filter") ProjectFilterRequest filterRequest) {
        int pageSize = 10;
        if (pageNo == null || pageNo == 0) {
            pageNo = 1;
        }
        if (filterRequest.getKeyword() == null) {
            filterRequest.setKeyword("");
        }
        Page<Project> projects = projectService.findPaginated(pageNo, pageSize, filterRequest);
        List<Status> statusList = statusService.listAll();
        List<Language> languages = languageService.listAll();
        model.addAttribute("languageList", languages);
        model.addAttribute("filterRequest", filterRequest);
        model.addAttribute("filter", new ProjectFilterRequest());
        model.addAttribute("statusList", statusList);
        model.addAttribute("projects", projects);
        return "viewproject";
    }

    @RequestMapping("/add")
    public String loadAddEmployee(ModelMap model) {
        List<Status> statusList = statusService.listAll();
        List<Language> languages = languageService.listAll();
        model.addAttribute("languageList", languages);
        model.addAttribute("projects", new Project());
        model.addAttribute("statusList", statusList);
        return "addproject";
    }

    @PostMapping(value = "save")
    public String save(@ModelAttribute("projects") Project project) {
        projectService.saveOrUpdateProject(project);
        return "redirect:";
    }

    @PostMapping(value = "save/employee")
    public String saveEmployee(ProjectManage projectManage) {
        for (Employee employee : projectManage.getEmployeeList()) {
            ManageProjectKey manageProjectKey = new ManageProjectKey();
            manageProjectKey.setProjectId(projectManage.getProject().getId());
            manageProjectKey.setEmployeeId(employee.getId());
            projectManage.setId(manageProjectKey);
            projectManage.setEmployee(employee);
            projectManage.setIsDelete(false);
            Status status = new Status();
            status.setId(1);
            status.setName("Active");
            projectManage.setStatus(status);
            projectManageService.saveOrUpdateProject(projectManage);
        }

        return "redirect:../../project/edit/"+ projectManage.getProject().getId();
    }

    @RequestMapping(value = "edit/{id}")
    public String loadDetail(@PathVariable("id") Integer id, Model model) {
        Project project = projectService.findById(id);
        List<Employee> employeeList = employeeService.listEmplOfProject(project);
        List<Status> statusList = statusService.listAll();
        List<Language> languages = languageService.listAll();
        Set<ProjectManage> employees = project.getProjectManages();
        model.addAttribute("history", new ProjectManage());
        model.addAttribute("employees", employeeList);
        model.addAttribute("projects", project);
        model.addAttribute("languageList", languages);
        model.addAttribute("employeeselected", employees);
        model.addAttribute("statusList", statusList);
        return "detail_project";
    }
}
