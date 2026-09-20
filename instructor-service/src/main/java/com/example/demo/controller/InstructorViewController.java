package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.InstructorRepository;

@Controller
public class InstructorViewController {
private InstructorRepository instructorRepository;

public InstructorViewController(InstructorRepository instructorRepository) {
	super();
	this.instructorRepository = instructorRepository;
}


@GetMapping("/instructors-ui")
public String InstructorsPage(ModelMap model)
{
model.addAttribute("instructors", instructorRepository.findAll());	
return "instructor";
}
}
