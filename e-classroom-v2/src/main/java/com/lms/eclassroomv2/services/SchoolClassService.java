package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.SchoolClass;
import com.lms.eclassroomv2.repository.SchoolClassRepository;

@Service
public class SchoolClassService {

	@Autowired
	SchoolClassRepository schoolClassRepository;
	
	public List<SchoolClass> getAllSchoolClasses() {
		return schoolClassRepository.findAll();
	}
	
	public SchoolClass getSchoolClassById(Long schoolClassId) {
		return schoolClassRepository.findById(schoolClassId).orElse(null);
	}
	
	public SchoolClass newSchoolClass(SchoolClass scClass) {
		return schoolClassRepository.save(scClass);
	}
	
	public SchoolClass editSchoolClass(Long scClassId, SchoolClass scClass) {
		SchoolClass schoolClass = getSchoolClassById(scClassId);
		schoolClass = scClass;
		return schoolClassRepository.save(schoolClass);
	}

}
