package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Course;
import com.lms.eclassroomv2.services.CourseService;

@RestController
@RequestMapping(value = "api/courses")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@GetMapping()
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping(value = "/{courseId}")
	public Course getCourseById(@PathVariable Long courseId) {
		return courseService.getCourseById(courseId);
	}
}
