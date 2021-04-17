package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Teacher;
import com.lms.eclassroomv2.services.TeacherService;

@RestController
@RequestMapping(value = "api/teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping()
	public List<Teacher> getAllTeachers() {
		return teacherService.getAllTeachers();
	}

}
