package com.lms.eclassroomv2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.model.Student;
import com.lms.eclassroomv2.model.dto.StudentDto;
import com.lms.eclassroomv2.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	StudentClassService stClassService;
	
	@Autowired
	AuthorityService authorityService;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public List<Student> getStudentsByClassId(Long classId) {
		return studentRepository.findByStudentClassId(classId);
	}
	
	public Student addNewStudent (StudentDto studentDto) {
		Student student = new Student();
		student.setEmail(studentDto.getEmail());
		student.setUsername(studentDto.getUsername());
		student.setPassword(studentDto.getPassword());
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setEnabled(true);
		student.setStudentClass(stClassService.getStudentClassById(studentDto.getStClassId()));
		
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityByName("ROLE_STUDENT"));
		student.setAuthorities(authorities);
		
		return studentRepository.save(student);
	}
}
