package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseViewController {

    @GetMapping("/course")
    public String coursePage(Model model) {
        model.addAttribute("serviceName", "COURSE-SERVICE");
        return "course";
    }
}