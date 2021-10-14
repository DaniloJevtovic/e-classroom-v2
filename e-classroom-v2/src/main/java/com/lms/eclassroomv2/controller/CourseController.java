package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/page")
	public Page<Course> getAllCoursesPagination(@PageableDefault(size = 10) Pageable pageable) {
		return courseService.getAllCoursesPagination(pageable);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{courseId}")
	public Course getCourseById(@PathVariable Long courseId) {
		return courseService.getCourseById(courseId);
	}

	// Svi predmeti za profesora
	@PreAuthorize("hasRole('ADMIN') or hasRole ('TEACHER')")
	@GetMapping("/teacher/{teacherId}")
	public List<Course> getAllCoursesForTeacher(@PathVariable Long teacherId) {
		return courseService.getAllCoursesForTeacher(teacherId);
	}

	// samo aktivni predmeti za profesora
	@PreAuthorize("hasRole('ADMIN') or hasRole ('TEACHER')")
	@GetMapping("/active/teacher/{teacherId}")
	public List<Course> getAllActiveCoursesForTeacher(@PathVariable Long teacherId) {
		return courseService.getAllActiveCoursesForTeacher(teacherId);
	}

	// svi predmeti za razred
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/schoolClass/{scId}")
	public List<Course> getAllCoursesForSchoolClass(@PathVariable Long scId) {
		return courseService.getAllCoursesForSchoolClass(scId);
	}

	// aktivni predmeti za razred
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/active/schoolClass/{scId}")
	public List<Course> getAllActiveCoursesForSchoolClass(@PathVariable Long scId) {
		return courseService.getAllActiveCoursesForSchoolClass(scId);
	}

	// svi predmeti za odjeljenje (i obrisani)
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping("/stClass/{stId}")
	public List<Course> getAllCoursesForStudentClass(@PathVariable Long stId) {
		return courseService.getCoursesForStudentClass(stId);
	}
	
	// samo aktivni predmeti za odjeljenje
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping("/active/stClass/{stId}")
	public List<Course> getAllActiveCoursesForStudentClass(@PathVariable Long stId) {
		return courseService.getActiveCoursesForStudentClass(stId);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addNewCourse(@RequestBody CourseDto courseDto) {
		return courseService.addCourse(courseDto);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping("/{courseId}")
	public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
		return courseService.updateCourse(courseId, courseDto);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@DeleteMapping("/{courseId}")
	public void deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourse(courseId);
	}
}
