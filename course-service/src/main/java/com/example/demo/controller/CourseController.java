

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

import com.example.demo.dao.CourseRepository;
import com.example.demo.model.Course;

@CrossOrigin(origins = "*")
@RequestMapping("/courses") 
@RestController
public class CourseController {
private final CourseRepository courseRepository;

public CourseController(CourseRepository courseRepository) {

	this.courseRepository = courseRepository;
}


@PostMapping
public ResponseEntity<Course> addCourse(@RequestBody Course course)
{
	Course course2=courseRepository.save(course) ;	
	return new  ResponseEntity<>(course2,HttpStatus.CREATED);

}
@GetMapping
public ResponseEntity<List<Course>> FindAllCourse()
{
List<Course> list=courseRepository.findAll();	
return ResponseEntity.ok(list);
}

@GetMapping("/{id}")
public ResponseEntity<Course> findCourseById(@PathVariable("id") int id)
{

		Course course=courseRepository.findById(id).orElse(null);	
		if(course==null)
		{
			 return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.ok(course);
}


@PutMapping
public ResponseEntity<Course> UpdateCourse(@RequestBody Course course)
{
	Course course2=courseRepository.save(course) ;	
	return  ResponseEntity.ok(course2);

}
@DeleteMapping("/{id}")
public ResponseEntity<Course> DeleteCourseById(@PathVariable("id") int id)
{
	Course course=courseRepository.findById(id).orElse(null);
	if(course==null)
	{
		return	ResponseEntity.notFound().build();

	}
 courseRepository.deleteById(id);	
return ResponseEntity.ok(course);	
}
}

