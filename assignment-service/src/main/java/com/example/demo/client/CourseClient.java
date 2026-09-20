package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Dto.courseDto;

@FeignClient(name="course-service")
public interface CourseClient {
@GetMapping("/courses/{id}")
public courseDto findCourseById(@PathVariable("id") int id);
}
