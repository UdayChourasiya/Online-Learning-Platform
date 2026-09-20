package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.ResultRepository;

@Controller
public class ResultViewController {

	private ResultRepository resultRepository;

	public ResultViewController(ResultRepository resultRepository) {
		super();
		this.resultRepository = resultRepository;
	}
	
	@GetMapping("/results-ui")
	public String ResultPage(ModelMap model)
	{
		model.addAttribute("results", resultRepository.findAll());
		return "result";
	}
}
