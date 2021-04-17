package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Student;

import com.lms.eclassroomv2.services.StudentService;

@RestController
@RequestMapping(value = "api/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping()
	public List<Student> getAllUsers() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(value = "/stClass/{classId}")
	public List<Student> getAllStudentsFromClass(@PathVariable Long classId) {
		return studentService.getStudentsByClassId(classId);
	}

}
