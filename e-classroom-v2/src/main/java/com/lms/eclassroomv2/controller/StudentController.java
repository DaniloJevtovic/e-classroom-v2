package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Student;
import com.lms.eclassroomv2.model.dto.StudentDto;
import com.lms.eclassroomv2.services.StudentService;

@RestController
@RequestMapping(value = "api/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping()
	public List<Student> getAllUsers() {
		return studentService.getAllStudents();
	}
	
	@PreAuthorize("hasRole ('ADMIN') or hasRole (TEACHER)")
	@GetMapping(value = "/stClass/{classId}")
	public List<Student> getAllStudentsFromClass(@PathVariable Long classId) {
		return studentService.getStudentsByClassId(classId);
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public Student addNewStudent(@RequestBody StudentDto studentDto) {
		return studentService.addNewStudent(studentDto);
	}

}
