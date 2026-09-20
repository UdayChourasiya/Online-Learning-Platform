package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.AssignmentClient;
import com.example.demo.client.EnrollmentClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.ResultRepository;
import com.example.demo.dto.AssignmentDto;
import com.example.demo.dto.EnrollmentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Result;

import feign.FeignException;

@RequestMapping("/results")
@RestController
public class ResultController {
private final ResultRepository resultRepository;
private final StudentClient studentClient;
private final AssignmentClient assignmentClient;
private final EnrollmentClient enrollmentClient;


public ResultController(ResultRepository resultRepository, StudentClient studentClient,
		AssignmentClient assignmentClient, EnrollmentClient enrollmentClient) {

	this.resultRepository = resultRepository;
	this.studentClient = studentClient;
	this.assignmentClient = assignmentClient;
	this.enrollmentClient = enrollmentClient;
}
@PostMapping
public ResponseEntity<?> addResult(@RequestBody Result result,@RequestParam("studentId") int StudentId,@RequestParam("assignmentId")int AssignmentId,@RequestParam("enrollmentId") int EnrollmentId)
{
	// Check Student
	try {
	StudentDto studentDto=studentClient.findStudentById(StudentId);
	}
	catch(FeignException.NotFound e)
	{
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("StudentID "+StudentId+"  Not Found");
	}
	
	//Check Assignment
	try {
	AssignmentDto assignmentDto=assignmentClient.findAssignmentById(AssignmentId);
	}
	catch(FeignException.NotFound e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("AssignmentId  "+AssignmentId+"Not Found");
	}
	//Check Enrollment
	try {
	EnrollmentDto enrollmentDto=enrollmentClient.findEnrollmentById(EnrollmentId);
	}
	catch(FeignException.NotFound e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("EnrollmentId  "+EnrollmentId+"Not Found");
	}
	result.setAssignmentId(AssignmentId);
	result.setEnrollmentId(EnrollmentId);
	result.setResultDate(LocalDate.now());
	result.setStudentId(StudentId);
	Result result1=resultRepository.save(result);
return new ResponseEntity<>(result1,HttpStatus.CREATED);	
}
@GetMapping
public ResponseEntity<List<Result>> findAllResult()
{
	List<Result> list=resultRepository.findAll();
	return ResponseEntity.ok(list);
}

@GetMapping("/{id}")
public ResponseEntity<Result> findResultById(@PathVariable("id") int id)
{
	Result result=resultRepository.findById(id).orElse(null);	
	if(result==null)
	{
		return ResponseEntity.notFound().build();
	}
return ResponseEntity.ok(result);
}
@PutMapping
public ResponseEntity<Result> UpdateResult(@RequestBody Result result)
{
	/*
	result.setAssignmentId(result.getAssignmentId());
	result.setEnrollmentId(result.getEnrollmentId());
	result.setResultDate(LocalDate.now());
	result.setStudentId(result.getStudentId());
	*/
	Result result1=resultRepository.save(result);	
	return ResponseEntity.ok(result1);
}
@DeleteMapping("/{id}")
public ResponseEntity<Result> DeleteById(@PathVariable("id") int id)
{
	Result result=resultRepository.findById(id).orElse(null);	
	if(result==null)
	{
		return ResponseEntity.notFound().build();
	}

resultRepository.deleteById(id);	
return ResponseEntity.ok(result);	
}
}
