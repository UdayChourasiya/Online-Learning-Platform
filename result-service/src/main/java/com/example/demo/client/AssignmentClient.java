package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.AssignmentDto;

@FeignClient(name="assignment-service")
public interface AssignmentClient {
	@GetMapping("/assignments/{id}")
public AssignmentDto findAssignmentById(@PathVariable("id") int id);
}
