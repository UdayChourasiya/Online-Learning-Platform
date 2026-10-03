package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultViewController {

    @GetMapping("/result")
    public String resultPage(Model model) {
        model.addAttribute("serviceName", "RESULT-SERVICE");
        return "result";
    }
}