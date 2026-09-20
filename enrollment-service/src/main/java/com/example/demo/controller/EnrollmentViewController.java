package com.example.demo.controller;

import com.example.demo.dao.EnrollmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnrollmentViewController {
private final EnrollmentRepository enrollmentRepository;
private EnrollmentController enrollmentController;

public EnrollmentViewController(EnrollmentController enrollmentController, EnrollmentRepository enrollmentRepository) {
	super();
	this.enrollmentController = enrollmentController;
	this.enrollmentRepository = enrollmentRepository;
}


@GetMapping("/enrollments-ui")
public String EnrollmentPage(ModelMap model)
{
	model.addAttribute("enrollments",enrollmentRepository.findAll());
return "enrollment";	
}
}
