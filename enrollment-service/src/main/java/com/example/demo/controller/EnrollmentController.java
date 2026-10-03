package com.example.demo.controller;

import java.net.ResponseCache;
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

import com.example.demo.client.CourseClient;
import com.example.demo.client.StudentClient;
import com.example.demo.dao.EnrollmentRepository;
import com.example.demo.dto.CourseDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Enrollment;

import feign.FeignException;

@CrossOrigin(origins = "*")
@RequestMapping("/enrollments")
@RestController
public class EnrollmentController {
private final EnrollmentRepository enrollmentRepository;
private final StudentClient studentClient;
private final CourseClient courseClient;

public EnrollmentController(EnrollmentRepository enrollmentRepository, StudentClient studentClient,
		CourseClient courseClient) {

	this.enrollmentRepository = enrollmentRepository;
	this.studentClient = studentClient;
	this.courseClient = courseClient;
}

@PostMapping
public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment ,@RequestParam("courseId") int CourseId,@RequestParam("studentId") int StudentId)
{
	//check Student
	try {
		StudentDto studentDto=studentClient.findStudentById(StudentId);
		
	}
	catch(FeignException.NotFound e) 
	{
		return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Student ID " + StudentId + " not found");
	}
	//check Course
	try{
		CourseDto courseDto=courseClient.findCourseById(CourseId);
	}
	catch(FeignException.NotFound e)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("  CourseID   " +CourseId+"  not found  ");
	}
	
	
	enrollment.setCourseid(CourseId);
	enrollment.setEnrollmentDate(LocalDate.now());
	enrollment.setStudentid(StudentId);

	Enrollment enrollment1=enrollmentRepository.save(enrollment);
	return new ResponseEntity<>(enrollment1,HttpStatus.CREATED);
}
@GetMapping
public ResponseEntity<List<Enrollment>> findAll()
{
	List<Enrollment> list=enrollmentRepository.findAll();	
return ResponseEntity.ok(list);
}

@GetMapping("/{id}")
public ResponseEntity<Enrollment> findEnrollmentById(@PathVariable("id")int id)
{
	Enrollment enrollment=enrollmentRepository.findById(id).orElse(null);	
if(enrollment==null)
{
return ResponseEntity.notFound().build();
}
return ResponseEntity.ok(enrollment);
}


@PutMapping
public ResponseEntity<Enrollment> UpdateEnrollment(@RequestBody Enrollment enrollment)
{
	
	
	enrollment.setEnrollmentDate(LocalDate.now());
	enrollment.setCourseid(enrollment.getCourseid());
	enrollment.setStudentid(enrollment.getStudentid());
	Enrollment enrollment1=enrollmentRepository.save(enrollment);	
	return ResponseEntity.ok(enrollment1);
}

@DeleteMapping("/{id}")
public ResponseEntity<Enrollment>  DeleteById(@PathVariable("id")int id)
{
	Enrollment enrollment=enrollmentRepository.findById(id).orElse(null);	
	if(enrollment==null)
	{
		return	ResponseEntity.notFound().build();
}

enrollmentRepository.deleteById(id);	
return ResponseEntity.ok(enrollment);
}

}
