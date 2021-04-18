package com.lms.eclassroomv2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.model.Teacher;
import com.lms.eclassroomv2.model.dto.TeacherDto;
import com.lms.eclassroomv2.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	AuthorityService authorityService;

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher getTeacherById(Long teacherId) {
		return teacherRepository.findById(teacherId).orElse(null);
	}

	public Teacher newTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		teacher.setUsername(teacherDto.getUsername());
		teacher.setEmail(teacherDto.getEmail());
		teacher.setPassword(teacherDto.getPassword());
		teacher.setFirstName(teacherDto.getFirstName());
		teacher.setLastName(teacherDto.getLastName());
		teacher.setSubjects(teacherDto.getSubjects());
		teacher.setEnabled(true);
		
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityByName("ROLE_TEACHER"));
		teacher.setAuthorities(authorities);

		return teacherRepository.save(teacher);
	}
}
