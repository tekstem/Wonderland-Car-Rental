package edu.miu.cs.cs425.carsystem.controller;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.model.Employee;
import edu.miu.cs.cs425.carsystem.service.CarService;
import edu.miu.cs.cs425.carsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/employee", "/register/employee"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = {"/list"})
    public ModelAndView listOfEmployees(@RequestParam(defaultValue = "0") int pageNo){
        var modelAndView =new ModelAndView();
        var employees= employeeService.getAllEmployeePaged(pageNo);
        modelAndView.addObject("employees",employees);
        modelAndView.addObject("currentPageNo",pageNo);
        modelAndView.setViewName("secured/employee/list");
        return modelAndView;
    }

    @GetMapping(value = {"/new"})
    public String displayNewEmployeeForm(Model model) {
        model.addAttribute("employee", Employee.build(null,
                null,null,null,null,null));
        return "secured/employee/new";
    }
    @PostMapping(value = "/new")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("employee",employee);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/employee/new";
        }
        employeeService.addNewEmployee(employee);
        return "redirect:/register/employee/list";

    }


    @GetMapping(value = "/delete/{employeeId}")
    public String deleteEmployeeById( @PathVariable Integer employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/register/employee/list";
    }

    @GetMapping(value = "/edit/{employeeId}")
    public String editEmployee( @PathVariable Integer employeeId, Model model){
        var employee= employeeService.getEmployeeById(employeeId);
        if(employee!=null){
            model.addAttribute("employee", employee);
            return "secured/employee/edit";
        }
        return "redirect:/register/employee/list";
    }
    @PostMapping(value = {"/update"})
    public String updateEmployees(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/employee/edit";
        }

        employeeService.updateEmployee(employee);
        return "redirect:/register/employee/list";
    }

   
/*
    @GetMapping(value = {"/search"})
    public ModelAndView searchCars(@RequestParam String searchString) {
        var modelAndView = new ModelAndView();
        var car=carService.searchCarByRegistrationNumberOrModelOrMakeOrYear(searchString);
        modelAndView.addObject("car", car);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("secured/car/searchResult");
        return modelAndView;
    }*/
}
