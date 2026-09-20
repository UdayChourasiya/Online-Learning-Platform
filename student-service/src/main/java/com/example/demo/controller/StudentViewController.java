package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.StudentRepository;

import ch.qos.logback.core.model.Model;

@Controller
public class StudentViewController {
	   private final StudentRepository studentRepository;

	    public StudentViewController(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    @GetMapping("/students-ui")
	    public String studentPage(ModelMap model) {

	        model.addAttribute("students", studentRepository.findAll());

	        return "student";
	    }
}
