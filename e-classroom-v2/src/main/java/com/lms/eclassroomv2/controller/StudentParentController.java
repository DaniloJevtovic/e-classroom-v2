package com.lms.eclassroomv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.StudentParent;
import com.lms.eclassroomv2.services.StudentParentService;

@RestController
@RequestMapping(value = "api/stParents")
public class StudentParentController {
	
	@Autowired
	StudentParentService stParentService;
	
	@PreAuthorize("hasRole ('ADMIN') or hasRole ('TEACHER')")
	@GetMapping(value = "/{stParentId}")
	public StudentParent getStParentById(@PathVariable Long stParentId) {
		return stParentService.getStParentById(stParentId);
	}
	
	@PreAuthorize("hasRole ('ADMIN')")
	@PostMapping
	public StudentParent saveParent(@RequestBody StudentParent studentParent) {
		return stParentService.saveStParent(studentParent);
	}

}
