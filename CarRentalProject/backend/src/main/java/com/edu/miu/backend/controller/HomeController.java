package com.edu.miu.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ResponseEntity<?> welcome() {
        return new ResponseEntity<>(new HashMap<>() {{
            put("message", "welcome to WONDERLAND CAR RENTAL SYSTEM backend");
        }}, HttpStatus.OK);
    }
}
