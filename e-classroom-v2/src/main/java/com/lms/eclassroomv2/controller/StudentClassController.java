package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.StudentClass;
import com.lms.eclassroomv2.model.dto.StudentClassDto;
import com.lms.eclassroomv2.services.StudentClassService;

@RestController
@RequestMapping("api/stClasses")
public class StudentClassController {

	@Autowired
	StudentClassService stClassService;

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping
	public List<StudentClass> getAllStudentClasses() {
		return stClassService.getAllStudentClasses();
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/{stClassId}")
	public StudentClass getStudentClassById(@PathVariable Long stClassId) {
		return stClassService.getStudentClassById(stClassId);
	}

	@GetMapping("/scClass/{scClassId}")
	public List<StudentClass> getAllStudentClassesForSchoolClass(@PathVariable Long scClassId) {
		return stClassService.getAllStClassesForScClass(scClassId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER')")
	@GetMapping("/course/{courseId}")
	public List<StudentClass> getAllStudentClassesForCourse(@PathVariable Long courseId) {
		return stClassService.getAllStClassesForCourse(courseId);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public ResponseEntity<?> newStudentClass(@RequestBody StudentClassDto stClassDto) {
		return stClassService.newStudentClass(stClassDto);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PutMapping("/{stClassId}")
	public ResponseEntity<?> editStudentClass(@PathVariable Long stClassId, @RequestBody StudentClassDto stClassDto) {
		return stClassService.editStudentClass(stClassId, stClassDto);
	}

}
