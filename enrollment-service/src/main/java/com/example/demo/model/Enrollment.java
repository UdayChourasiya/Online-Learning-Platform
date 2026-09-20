package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enrollment {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private int studentId;
private int courseId;
private LocalDate enrollmentDate;
private String status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getStudentid() {
	return studentId;
}
public void setStudentid(int studentid) {
	this.studentId = studentid;
}
public int getCourseid() {
	return courseId;
}
public void setCourseid(int courseid) {
	this.courseId = courseid;
}
public LocalDate getEnrollmentDate() {
	return enrollmentDate;
}
public void setEnrollmentDate(LocalDate enrollmentDate) {
	this.enrollmentDate = enrollmentDate;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
