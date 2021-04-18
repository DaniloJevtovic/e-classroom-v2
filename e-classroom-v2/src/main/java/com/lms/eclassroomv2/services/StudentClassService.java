package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<StudentClass> getAllStudentClasses() {
		return studentClassRepository.findAll();
	}

	public StudentClass getStudentClassById(Long studentClassId) {
		return studentClassRepository.findById(studentClassId).orElse(null);
	}
	
	//sva odjeljenja za razred
	public List<StudentClass> getAllStClassesForScClass(Long schoolClassId) {
		return studentClassRepository.findBySchoolClassId(schoolClassId);
	}

	public StudentClass newStudentClass(StudentClassDto scClassDto) {
		StudentClass studentClass = new StudentClass();
		studentClass.setName(scClassDto.getName());
		studentClass.setDescription(scClassDto.getDescription());
		studentClass.setSchoolClass(scClassService.getSchoolClassById(scClassDto.getScClassId()));

		return studentClassRepository.save(studentClass);
	}

}
