package edu.miu.cs.cs425.carsystem.controller;

import edu.miu.cs.cs425.carsystem.model.Car;
import edu.miu.cs.cs425.carsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller


public class carController {
    @Autowired
    private CarService carService;

@GetMapping(value = {"/car/list"})
    public ModelAndView listOfCars(@RequestParam(defaultValue = "0") int pageNo){
    var modelAndView =new ModelAndView();
    var cars= carService.getAllCarPaged(pageNo);
     modelAndView.addObject("cars",cars);
     modelAndView.addObject("currentPageNo",pageNo);
     modelAndView.setViewName("secured/car/list");
     return modelAndView;

}

}
