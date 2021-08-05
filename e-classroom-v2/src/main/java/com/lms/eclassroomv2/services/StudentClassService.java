package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.StudentClass;
import com.lms.eclassroomv2.model.dto.StudentClassDto;
import com.lms.eclassroomv2.repository.StudentClassRepository;

@Service
public class StudentClassService {

	@Autowired
	StudentClassRepository studentClassRepository;

	@Autowired
	SchoolClassService scClassService;

	@Autowired
	CourseService courseService;

	public List<StudentClass> getAllStudentClasses() {
		return studentClassRepository.findAll();
	}

	public StudentClass getStudentClassById(Long studentClassId) {
		return studentClassRepository.findById(studentClassId).orElse(null);
	}

	// sva odjeljenja za razred
	public List<StudentClass> getAllStClassesForScClass(Long schoolClassId) {
		return studentClassRepository.findBySchoolClassId(schoolClassId);
	}

	// sva odjeljenja za predmet - profesor
	public List<StudentClass> getAllStClassesForCourse(Long courseId) {
		// izvucem razred kojem predmet pripada
		Long courseClassId = courseService.getCourseById(courseId).getSchoolClass().getId();
		// za taj id razreda izvucem sva odjeljenja koja mu pripadaju
		return getAllStClassesForScClass(courseClassId);
	}

	public ResponseEntity<?> newStudentClass(StudentClassDto scClassDto) {

		try {
			StudentClass studentClass = new StudentClass();
			studentClass.setName(scClassDto.getName());
			studentClass.setDescription(scClassDto.getDescription());
			studentClass.setSchoolClass(scClassService.getSchoolClassById(scClassDto.getScClassId()));

			//studentClassRepository.save(studentClass);
			//return new ResponseEntity<>("Odjeljenje uspjesno kreirano", HttpStatus.OK);
			
			//return ResponseEntity.ok(studentClassRepository.save(studentClass));
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", studentClassRepository.save(studentClass));
			res.put("message", "Odjeljenje uspjesno kreirano");
			
			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce kreirati odjeljenje", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> editStudentClass(Long stClassId, StudentClassDto scClassDto) {
		try {
			StudentClass studentClass = getStudentClassById(stClassId);
			studentClass.setName(scClassDto.getName());
			studentClass.setDescription(scClassDto.getDescription());
			studentClass.setSchoolClass(scClassService.getSchoolClassById(scClassDto.getScClassId()));

			//studentClassRepository.save(studentClass);
			//return new ResponseEntity<>("Odjeljenje uspjesno izmjenjeno", HttpStatus.OK);
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", studentClassRepository.save(studentClass));
			res.put("message", "Odjeljenje uspjesno azurirano");
			
			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce izmjeniti odjeljenje.", HttpStatus.BAD_REQUEST);
		}

	}

}
