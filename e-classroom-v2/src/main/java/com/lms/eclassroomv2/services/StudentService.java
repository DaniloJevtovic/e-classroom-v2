package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	CourseService courseService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AuthorityService authorityService;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId).orElse(null);
	}

	// svi ucenici iz odjeljenja
	public List<Student> getStudentsByStudentClass(Long classId) {
		return studentRepository.findByStudentClassId(classId);
	}

	// svi ucenici iz razreda
	public List<Student> getStudentsFromSchoolClass(Long schoolClassId) {
		return studentRepository.findByStudentClassSchoolClassId(schoolClassId);
	}

	// svi ucenici na predmetu
	public List<Student> getStudentsFromCourse(Long courseId) {
		// pronadjem id-razreda kojem predmet pripada
		Long courseClassId = courseService.getCourseById(courseId).getSchoolClass().getId();
		// za id-tog razreda ucitam sve ucenike jer svi oni slusaju taj predmet
		return getStudentsFromSchoolClass(courseClassId);
	}

	public Student addNewStudent(StudentDto studentDto) {
		Student student = new Student();
		student.setEmail(studentDto.getEmail());
		student.setUsername(studentDto.getUsername());
		student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setEnabled(true);
		student.setStudentClass(stClassService.getStudentClassById(studentDto.getStClassId()));

		List<Authority> authorities = authorityService.findByname("ROLE_STUDENT");
		student.setAuthorities(authorities);

		return studentRepository.save(student);
	}
}
