package com.example.projectmanagement.controler;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Language;
import com.example.projectmanagement.entities.Role;
import com.example.projectmanagement.entities.Status;
import com.example.projectmanagement.service.*;
import com.example.projectmanagement.settings.EmployeeFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LanguageService languageService;



    @RequestMapping("")
    public String findPaginated(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap model,
                                @ModelAttribute(name = "filter") EmployeeFilterRequest filterRequest) {
        int pageSize = 10;
        if (pageNo == null || pageNo == 0) {
            pageNo = 1;
        }
        if(filterRequest.getKeyword() == null){
            filterRequest.setKeyword("");
        }
        Page<Employee> employeeList = employeeService.findPaginated(pageNo, pageSize,filterRequest);
        List<Status> statusList = statusService.listAll();
        if (employeeList.isEmpty()) {
            model.addAttribute("mess", "There are no employees on the list!");
        }
        List<Role> roleList = roleService.listAll();
        List<Language> languages = languageService.listAll();
        model.addAttribute("filter",new EmployeeFilterRequest());
        model.addAttribute("languageList", languages);
        model.addAttribute("roleList", roleList);
        model.addAttribute("statusList", statusList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("filterRequest",filterRequest);
        return "index";
    }

    @RequestMapping("/add")
    public String loadAddEmployee(ModelMap model) {
        List<Status> statusList = statusService.listAll();
        List<Role> roleList = roleService.listAll();
        List<Language> languages = languageService.listAll();
        model.addAttribute("languageList", languages);
        model.addAttribute("detailEmployee", new Employee());
        model.addAttribute("roleList", roleList);
        model.addAttribute("statusList", statusList);
        return "add";
    }

    @PostMapping(value = "save")
    public String save(@ModelAttribute("detailEmployee") Employee employee) {
        employeeService.saveOrUpdateEmployee(employee);
        return "redirect:";
    }

    @RequestMapping(value = "edit/{id}")
    public String loadDetail(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = employeeService.findById(id);
        List<Role> roleList = roleService.listAll();
        List<Status> statusList = statusService.listAll();
        List<Language> languages = languageService.listAll();
        model.addAttribute("detailEmployee", employee);
        model.addAttribute("languageList", languages);
        model.addAttribute("roleList", roleList);
        model.addAttribute("statusList", statusList);
        return "details";
    }
}
