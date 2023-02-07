package edu.miu.cs.cs425.carsystem.controller;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.model.Customer;
import edu.miu.cs.cs425.carsystem.repository.CustomerRepository;
import edu.miu.cs.cs425.carsystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/customer","/register/customer"})
public class CustomerController {
  @Autowired
    private CustomerService customerService;

 @GetMapping(value = "/list")
  public ModelAndView listOfCustomers( @RequestParam(defaultValue = "0") int pageNo){
      var modelAndView =new ModelAndView();
      var customers= customerService.getAllCustomersPaged(pageNo);

      modelAndView.addObject("customers", customers);
      modelAndView.addObject("currentPageNo",pageNo);
      modelAndView.setViewName("secured/customer/list");
      return modelAndView;

  }

  @GetMapping(value = "/new")
  public String registerNewCustomer(Model model){
     model.addAttribute("customer",Customer.build(null,null, null, null,
             null,null,null));
    return "/secured/customer/new";
  }

  @PostMapping(value = "/new")
    public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model){
     if(bindingResult.hasErrors()){
         model.addAttribute("customers", customer);
         model.addAttribute("errors", bindingResult.getAllErrors());
         return "secured/customer/new";
     }
     customerService.addNewCustomer(customer);
     return "redirect:/register/customer/list";
  }

    @PostMapping(value = "/update")
    public String updateCustomers(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("customer", customer);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/customer/edit";
        }
        customerService.updateCustomer(customer);
        return "redirect:/register/customer/list";
    }

    @GetMapping(value = "/edit/{customerId}")
    public String editCustomers( @PathVariable Integer customerId, Model model){
     var customer= customerService.getCustomerById(customerId);
    if(customer!=null){
        model.addAttribute("customer",customer);
        return "secured/customer/edit";
    }
    return "redirect:/register/customer/list";
    }


    @GetMapping(value = "/delete/{customerId}")
    public String deleteCustomerById( @PathVariable Integer customerId){
        customerService.deleteCustomerById(customerId);
        return "redirect:/register/customer/list";
    }

}
