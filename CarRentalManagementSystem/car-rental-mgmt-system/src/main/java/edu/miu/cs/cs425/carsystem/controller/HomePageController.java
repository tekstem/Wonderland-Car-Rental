package edu.miu.cs.cs425.carsystem.controller;

import edu.miu.cs.cs425.carsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomePageController {
    @Autowired
    private CarService carService;

    @GetMapping(value =  "/home")
    public String displayHomePage(){
        return "public/home/index";
    }

    @GetMapping("/about")
    public String displayAboutPage(){
        return "public/home/about";
    }


}
