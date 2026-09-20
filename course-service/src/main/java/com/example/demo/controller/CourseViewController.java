package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.CourseRepository;

@Controller
public class CourseViewController {
private CourseRepository courseRepository;

public CourseViewController(CourseRepository courseRepository) {
	super();
	this.courseRepository = courseRepository;
}


@GetMapping("/courses-ui")
public String CoursePage(ModelMap model)
{
model.addAttribute("courses", courseRepository.findAll());
return "course";
}
}
