package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	StudentParentService parentService;

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Page<Student> getAllStudentsPagination(Pageable pageable) {
		return studentRepository.findAll(pageable);
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

	public List<Student> getStudentsForParent(Long parentId) {
		return studentRepository.findByStParentId(parentId);
	}

	public ResponseEntity<?> addNewStudent(StudentDto studentDto) {
		try {
			Student student = new Student();
			student.setEmail(studentDto.getEmail());
			student.setUsername(studentDto.getUsername());
			student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setEnabled(true);
			student.setStudentClass(stClassService.getStudentClassById(studentDto.getStClassId()));
			student.setStParent(parentService.getStParentById(studentDto.getParentId()));

			List<Authority> authorities = authorityService.findByname("ROLE_STUDENT");
			student.setAuthorities(authorities);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", studentRepository.save(student));
			res.put("message", "Ucenik je uspjesno dodat u odjeljenje");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u dodavanju ucenika", HttpStatus.BAD_REQUEST);
		}

	}
}
