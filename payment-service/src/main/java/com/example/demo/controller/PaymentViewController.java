package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.PaymentRepository;

@Controller
public class PaymentViewController {
private PaymentRepository paymentRepository;

public PaymentViewController(PaymentRepository paymentRepository) {
	super();
	this.paymentRepository = paymentRepository;
}

@GetMapping("/payments-ui")
public String PaymentPage(ModelMap model)
{
model.addAttribute("payments", paymentRepository.findAll());
return "payment";

}
}
