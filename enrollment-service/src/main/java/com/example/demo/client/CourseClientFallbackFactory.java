package com.example.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.CourseDto;

import feign.FeignException;

@Component
public class CourseClientFallbackFactory implements FallbackFactory<CourseClient> {

    private static final Logger log = LoggerFactory.getLogger(CourseClientFallbackFactory.class);

    @Override
    public CourseClient create(Throwable cause) {
        return new CourseClient() {
            @Override
            public CourseDto findCourseById(int id) {
                log.error("Course call failed for id {} : {}", id, cause.toString());
                if (cause instanceof FeignException.NotFound) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id " + id);
                }
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                        "Course service is currently unavailable, please try again later");
            }
        };
    }
}
