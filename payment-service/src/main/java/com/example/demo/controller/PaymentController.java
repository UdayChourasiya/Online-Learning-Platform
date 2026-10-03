package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Payment;

import feign.FeignException;

@CrossOrigin(origins = "*")
@RequestMapping("/payments")
@RestController
public class PaymentController {
private final PaymentRepository paymentRepository;
private final StudentClient studentClient;
private final EnrollmentClient enrollmentClient;

public PaymentController(PaymentRepository paymentRepository, StudentClient studentClient,
		EnrollmentClient enrollmentClient) {

	this.paymentRepository = paymentRepository;
	this.studentClient = studentClient;
	this.enrollmentClient = enrollmentClient;
}
@PostMapping
public ResponseEntity<?> addPayment(@RequestBody Payment payment,@RequestParam("studentId") int StudentId,@RequestParam("enrollmentId") int EnrollementId)
{
	try {
	StudentDto studentDto=studentClient.findStudentById(StudentId);
	}catch(Exception e) 
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("StudentId  "+StudentId+ "   Not Found");
	}	
	try {
	EnrollmentDto enrollmentDto=enrollmentClient.findEnrollmentById(EnrollementId);
	}
	catch(Exception e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("EnrollmentId  "+EnrollementId+ "    NotFound");
	}
	
payment.setEnrollmentId(EnrollementId);
payment.setPaymentDate(LocalDate.now());
payment.setStudentId(StudentId);

Payment payment1=paymentRepository.save(payment);
return new ResponseEntity<>(payment1,HttpStatus.CREATED);	
}
@GetMapping
public ResponseEntity<List<Payment>> findAllPayment()
{
List<Payment> list=paymentRepository.findAll();	
return ResponseEntity.ok(list);
}
@GetMapping("/{id}")
public ResponseEntity<Payment> findPaymentById(@PathVariable("id") int id)
{
Payment payment=paymentRepository.findById(id).orElse(null);	
if(payment==null)
{
return ResponseEntity.notFound().build();	
}
return ResponseEntity.ok(payment);
}
@PutMapping
public ResponseEntity<Payment> UpdatePayment(@RequestBody Payment payment)
{
	Payment payment1=paymentRepository.save(payment);
	return ResponseEntity.ok(payment1);
}
@DeleteMapping("/{id}")
public ResponseEntity<Payment> deleteById(@PathVariable("id") int id)
{
	Payment payment=paymentRepository.findById(id).orElse(null);	
	if(payment==null)
	{
	return ResponseEntity.notFound().build();	
	}
	paymentRepository.deleteById(id);
	return ResponseEntity.ok(payment);
}


}
