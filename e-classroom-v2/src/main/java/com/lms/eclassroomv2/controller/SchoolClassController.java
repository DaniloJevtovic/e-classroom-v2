package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.SchoolClass;
import com.lms.eclassroomv2.services.SchoolClassService;

@RestController
@RequestMapping("api/scClasses")
public class SchoolClassController {

	@Autowired
	SchoolClassService scClassService;
	
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping()
	public List<SchoolClass> getAllSchoolClasses() {
		return scClassService.getAllSchoolClasses();
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@GetMapping("/{scClassId")
	public SchoolClass getSchoolClassById(@PathVariable Long scClassId) {
		return scClassService.getSchoolClassById(scClassId);
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public SchoolClass newSchoolClass(@RequestBody SchoolClass schoolClass) {
		return scClassService.newSchoolClass(schoolClass);
	}
}
