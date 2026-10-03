package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssignmentViewController {

    // http://localhost:8086/assignment   (REST API /assignments par hai, isliye page singular par)
    @GetMapping("/assignment")
    public String assignmentPage(Model model) {
        model.addAttribute("serviceName", "ASSIGNMENT-SERVICE");
        return "assignment";
    }
}