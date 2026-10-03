package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollmentViewController {

    // http://localhost:8084/enrollment
    @GetMapping("/enrollment")
    public String enrollmentPage(Model model) {
        model.addAttribute("serviceName", "ENROLLMENT-SERVICE");
        return "enrollment";
    }
}