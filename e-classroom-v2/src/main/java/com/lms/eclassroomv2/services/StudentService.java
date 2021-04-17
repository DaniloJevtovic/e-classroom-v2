package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Student;
import com.lms.eclassroomv2.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public List<Student> getStudentsByClassId(Long classId) {
		return studentRepository.findByStudentClassId(classId);
	}
}
