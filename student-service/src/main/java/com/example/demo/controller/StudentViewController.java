package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentViewController {

  
    @GetMapping({"/", "/dashboard"})
    public String dashboardPage() {
        return "dashboard";
    }

     @GetMapping("/student")
    public String studentsPage(Model model) {
        model.addAttribute("serviceName", "STUDENT-SERVICE");
        return "student";
    }
}