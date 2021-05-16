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

	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/{studentId}")
	public Student getStudentById(@PathVariable Long studentId) {
		return studentService.getStudentById(studentId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER')")
	@GetMapping(value = "/stClass/{classId}")
	public List<Student> getAllStudentsFromStClass(@PathVariable Long classId) {
		return studentService.getStudentsByStudentClass(classId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER')")
	@GetMapping(value = "/scClass/{scClassId}")
	public List<Student> getStudentsFromSchoolClass(@PathVariable Long scClassId) {
		return studentService.getStudentsFromSchoolClass(scClassId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER') or hasRole ('PARENT')")
	@GetMapping(value = "/parent/{parentId}")
	public List<Student> getStudentsForParent(@PathVariable Long parentId) {
		return studentService.getStudentsForParent(parentId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER')")
	@GetMapping(value = "/course/{courseId}")
	public List<Student> getStudentsByCourse(@PathVariable Long courseId) {
		return studentService.getStudentsFromCourse(courseId);
	}

	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public Student addNewStudent(@RequestBody StudentDto studentDto) {
		return studentService.addNewStudent(studentDto);
	}

}
