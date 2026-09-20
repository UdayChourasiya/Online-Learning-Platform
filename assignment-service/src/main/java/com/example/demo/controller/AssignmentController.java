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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.InstructorDto;
import com.example.demo.Dto.courseDto;
import com.example.demo.client.CourseClient;
import com.example.demo.client.InstructorClient;
import com.example.demo.dao.AssignmentRepository;
import com.example.demo.model.Assignment;

import feign.FeignException;

@RequestMapping("/assignments")
@RestController
public class AssignmentController {

	private final AssignmentRepository assignmentRepository;
	private final InstructorClient instructorClient;
	private final CourseClient courseClient;
	public AssignmentController(AssignmentRepository assignmentRepository, InstructorClient instructorClient,
			CourseClient courseClient) {
		
		this.assignmentRepository = assignmentRepository;
		this.instructorClient = instructorClient;
		this.courseClient = courseClient;
	}
	
@PostMapping
public ResponseEntity<?> addAssignment(@RequestBody Assignment assignment,@RequestParam("courseId") int CourseId,@RequestParam("instructorId") int InstructorId)
{
	  // Check Instructor
    try {

        InstructorDto instructorDto =
                instructorClient.findInstructorById(InstructorId);

    } catch (FeignException.NotFound e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Instructor ID " + InstructorId + " not found");
    }
    
    // Check Course
    try {

        courseDto courseDto =
                courseClient.findCourseById(CourseId);

    } catch (FeignException.NotFound e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Course ID " + CourseId + " not found");
    }
	assignment.setCourseId(CourseId);
	assignment.setDueDate(LocalDate.now());
	assignment.setInstructorId(InstructorId);
	Assignment assignment2= assignmentRepository.save(assignment);
	 return new ResponseEntity<>(assignment2,HttpStatus.CREATED);
}
@GetMapping
public ResponseEntity<List<Assignment>> findAllAssignment()
{
List<Assignment> list=assignmentRepository.findAll();
return ResponseEntity.ok(list);
}
@GetMapping("/{id}")
public ResponseEntity<Assignment> findAssignmentById(@PathVariable("id") int id)
{
 Assignment assignment = assignmentRepository.findById(id).orElse(null);	
if(assignment==null)
{
return ResponseEntity.notFound().build();	
}
return ResponseEntity.ok(assignment);
}
@PutMapping
public ResponseEntity<Assignment>  UpdateAssignment(@RequestBody Assignment assignment)
{
	Assignment assignment1 =assignmentRepository.save(assignment);	
return ResponseEntity.ok(assignment1);
}

@DeleteMapping("/{id}")
public ResponseEntity<Assignment>  DeleteAssignmentById(@PathVariable("id") int id)
{
	Assignment assignment =	assignmentRepository.findById(id).orElse(null);
if(assignment==null)
{
	return ResponseEntity.notFound().build();
}

		 assignmentRepository.deleteById(id);	
return ResponseEntity.ok(assignment);
}
}


