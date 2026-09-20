package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.StudentDto;

import feign.FeignException;

@Component
public class StudentClientFallbackFactory implements FallbackFactory<StudentClient> {

    private static final Logger log = LoggerFactory.getLogger(StudentClientFallbackFactory.class);

    @Override
    public StudentClient create(Throwable cause) {
        return new StudentClient() {
            @Override
            public StudentDto findStudentById(int id) {
                log.error("Student call failed for id {} : {}", id, cause.toString());
                if (cause instanceof FeignException.NotFound) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id);
                }
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                        "Student service is currently unavailable, please try again later");
            }
        };
    }
}