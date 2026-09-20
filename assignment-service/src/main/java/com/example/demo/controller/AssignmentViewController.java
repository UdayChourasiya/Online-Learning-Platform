package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.AssignmentRepository;

@Controller
public class AssignmentViewController {
private AssignmentRepository assignmentRepository;

public AssignmentViewController(AssignmentRepository assignmentRepository) {
	super();
	this.assignmentRepository = assignmentRepository;
}


@GetMapping("/assignments-ui")
public String AssignmentPage(ModelMap model)
{
model.addAttribute("assignments", assignmentRepository.findAll());
return "assignment";
}
}
