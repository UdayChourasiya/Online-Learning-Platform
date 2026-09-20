package com.example.demo.controller;


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

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;

import jakarta.ws.rs.core.Response;

@RequestMapping("/students")
@RestController
public class StudentController {

	private final StudentRepository studentRepository;

	public StudentController(StudentRepository studentRepository) {
		
		this.studentRepository = studentRepository;
	}
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{

		Student stu=studentRepository.save(student);
		return new ResponseEntity<>(stu,HttpStatus.CREATED);
	}
		
	@GetMapping
	public ResponseEntity<List<Student>> findAllStudent()
	{
		
				List<Student> list=studentRepository.findAll();
				return ResponseEntity.ok(list);	
	}
	@GetMapping("/{id}")
	public ResponseEntity<Student> findStudentById(@PathVariable("id")int id)
	{
	 Student student	=studentRepository.findById(id).orElse(null);
	  if(student==null)
	  {
		  return ResponseEntity.notFound().build();
	  }
	  return ResponseEntity.ok(student);

	}
	@PutMapping
	public ResponseEntity<Student>  UpdateStudent(@RequestBody Student student)
	{
		Student stu=studentRepository.save(student);
		return ResponseEntity.ok(stu);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable("id") int id)
	{ 
	Student student=studentRepository.findById(id).orElse(null);
	if(student==null)
	{
		return	ResponseEntity.notFound().build();
		}
	studentRepository.delete(student);
return ResponseEntity.ok(student);	
	}
	
}

