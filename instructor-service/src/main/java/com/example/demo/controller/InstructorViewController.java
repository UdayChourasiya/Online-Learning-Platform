package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorViewController {

    // http://localhost:8083/instructor
    @GetMapping("/instructor")
    public String instructorPage(Model model) {
        model.addAttribute("serviceName", "INSTRUCTOR-SERVICE");
        return "instructor";
    }
}