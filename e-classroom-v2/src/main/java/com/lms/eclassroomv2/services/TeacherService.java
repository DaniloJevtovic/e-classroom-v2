package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.model.Teacher;
import com.lms.eclassroomv2.model.User;
import com.lms.eclassroomv2.model.dto.TeacherDto;
import com.lms.eclassroomv2.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	UserService userService;

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher getTeacherById(Long teacherId) {
		return teacherRepository.findById(teacherId).orElse(null);
	}

	public ResponseEntity<?> newTeacher(TeacherDto teacherDto) {

		User user = userService.getUserByEmail(teacherDto.getEmail());
		
		if(user != null) {
			return new ResponseEntity<>("Postoji vec registrovan taj profesor", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Teacher teacher = new Teacher();
			teacher.setUsername(teacherDto.getUsername());
			teacher.setEmail(teacherDto.getEmail());
			teacher.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
			teacher.setFirstName(teacherDto.getFirstName());
			teacher.setLastName(teacherDto.getLastName());
			teacher.setSubjects(teacherDto.getSubjects());
			teacher.setEnabled(true);

			List<Authority> authorities = authorityService.findByname("ROLE_TEACHER");
			teacher.setAuthorities(authorities);

			teacherRepository.save(teacher);

			return new ResponseEntity<>("Profesor uspjesno dodat", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce dodati profesora", HttpStatus.BAD_REQUEST);
		}

	}
}
