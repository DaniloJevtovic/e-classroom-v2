package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public SchoolClass getSchoolClassByName(String scName) {
		return schoolClassRepository.findByName(scName);
	}

	public ResponseEntity<?> newSchoolClass(SchoolClass scClass) {

		try {
			SchoolClass sc = getSchoolClassByName(scClass.getName());

			// provjeravam da li postoji vec kreiran taj razred kako ne bi bila 2 sa istim
			// imenom
			if (sc != null) {
				return new ResponseEntity<>("Postoji vec kreiran taj razred", HttpStatus.BAD_REQUEST);
			}

			schoolClassRepository.save(scClass);

			return new ResponseEntity<>("Razred uspjesno kreiran", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce kreirati razred", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> editSchoolClass(Long scClassId, SchoolClass scClass) {
		try {
			SchoolClass schoolClass = getSchoolClassById(scClassId);
			schoolClass.setName(scClass.getName());
			schoolClass.setDescription(scClass.getDescription());
			schoolClassRepository.save(schoolClass);

			return new ResponseEntity<>("Razred uspjesno azuriran", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce azurirati razred", HttpStatus.BAD_REQUEST);
		}

	}

}
