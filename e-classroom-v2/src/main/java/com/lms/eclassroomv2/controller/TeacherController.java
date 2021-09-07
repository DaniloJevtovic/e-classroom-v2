package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Teacher;
import com.lms.eclassroomv2.model.dto.TeacherDto;
import com.lms.eclassroomv2.services.TeacherService;

@RestController
@RequestMapping(value = "api/teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping()
	public List<Teacher> getAllTeachers() {
		return teacherService.getAllTeachers();
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/page")
	public Page<Teacher> getAllTeachersPagination(@PageableDefault(size = 10) Pageable pageable) {
		return teacherService.getAllTeachersPagination(pageable);
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/{teacherId}")
	public Teacher getTeacherById(@PathVariable Long teacherId) {
		return teacherService.getTeacherById(teacherId);
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public ResponseEntity<?> addNewTeacher(@RequestBody TeacherDto teacherDto) {
		return teacherService.newTeacher(teacherDto);
	}

}
