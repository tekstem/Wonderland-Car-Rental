package edu.miu.cs.cs425.carsystem.controller;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

@RequestMapping(value = {"/car", "/register/car"})
public class carController {
    @Autowired
    private CarService carService;

@GetMapping(value = {"/list"})
    public ModelAndView listOfCars(@RequestParam(defaultValue = "0") int pageNo){
    var modelAndView =new ModelAndView();
    var cars= carService.getAllCarPaged(pageNo);
     modelAndView.addObject("cars",cars);
     modelAndView.addObject("currentPageNo",pageNo);
     modelAndView.setViewName("secured/car/list");
     return modelAndView;
}

    @GetMapping(value = {"/new"})
    public String displayNewCarForm(Model model) {
        model.addAttribute("car", Car.build(null,
                null,null,null,null,null,null));
        return "secured/car/new";
    }
@PostMapping(value = "/new")
public String updateCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model){
    if(bindingResult.hasErrors()){
        model.addAttribute("car",car);
        model.addAttribute("errors", bindingResult.getAllErrors());
        return "secured/car/new";
    }
    carService.addNewCar(car);
    return "redirect:/register/car/list";

}

@GetMapping(value = "/delete/{carId}")
public String deleteCarById( @PathVariable Integer carId){
    carService.deleteCarById(carId);
  return "redirect:/register/car/list";
}

    @GetMapping(value = "/edit/{carId}")
    public String editCar( @PathVariable Integer carId, Model model){
        var car=carService.getByById(carId);
        if(car!=null){
            model.addAttribute("car", car);
            return "secured/car/edit";
        }
        return "redirect:/register/car/list";
    }
    @PostMapping(value = {"/update"})
    public String updateCars(@Valid @ModelAttribute("car") Car car,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("car", car);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/car/edit";
        }

        carService.updatecar(car);
        return "redirect:/register/car/list";
    }

}
