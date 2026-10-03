package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.InstructorRepository;

import com.example.demo.model.Instructor;

@CrossOrigin(origins = "*")
@RequestMapping("/instructors")
@RestController
public class InstructorController {
private final InstructorRepository instructorRepository;

public InstructorController(InstructorRepository instructorRepository) {

	this.instructorRepository = instructorRepository;
}
@PostMapping
public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor)
{
	Instructor instructor2=instructorRepository.save(instructor);	
	  return new ResponseEntity<>(instructor2,HttpStatus.CREATED);
}

@GetMapping
public ResponseEntity<List<Instructor>> findAllInstructor()
{
 List< Instructor >list=instructorRepository.findAll();	
return ResponseEntity.ok(list);
}

@GetMapping("/{id}")
public ResponseEntity<Instructor> findInstructorById(@PathVariable("id") int id)
{
	Instructor instructor=instructorRepository.findById(id).orElse(null);	
if(instructor==null)
{
return ResponseEntity.notFound().build();
}
return ResponseEntity.ok(instructor);
}


@PutMapping
public ResponseEntity<Instructor> UpdateInstructor(@RequestBody Instructor instructor)
{
	Instructor instructor2=instructorRepository.save(instructor);	
	return ResponseEntity.ok(instructor2);

}

@DeleteMapping("/{id}")
public ResponseEntity<Instructor> deleteInstructorById(@PathVariable("id")int id)
{
	Instructor instructor=instructorRepository.findById(id).orElse(null);
	if(instructor==null)
	{
		return ResponseEntity.notFound().build();
	}

			 instructorRepository.deleteById(id);	
	return ResponseEntity.ok(instructor);
}
}
