package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Course;
import com.lms.eclassroomv2.model.dto.CourseDto;
import com.lms.eclassroomv2.services.CourseService;

@RestController
@RequestMapping(value = "api/courses")
public class CourseController {

	@Autowired
	CourseService courseService;

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping()
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping(value = "/{courseId}")
	public Course getCourseById(@PathVariable Long courseId) {
		return courseService.getCourseById(courseId);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole ('TEACHER')")
	@GetMapping("/teacher/{teacherId}")
	public List<Course> getAllCoursesForTeacher(@PathVariable Long teacherId) {
		return courseService.getAllCoursesForTeacher(teacherId);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/schoolClass/{scId}")
	public List<Course> getAllCoursesForSchoolClass(@PathVariable Long scId) {
		return courseService.getAllCoursesForSchoolClass(scId);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public Course addNewCourse(@RequestBody CourseDto courseDto) {
		return courseService.addCourse(courseDto);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping("/{courseId}")
	public Course updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
		return courseService.updateCourse(courseId, courseDto);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@DeleteMapping("/{courseId}")
	public void deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
	}
}
