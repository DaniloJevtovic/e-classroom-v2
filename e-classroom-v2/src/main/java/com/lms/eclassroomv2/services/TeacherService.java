package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Teacher;
import com.lms.eclassroomv2.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}
}
