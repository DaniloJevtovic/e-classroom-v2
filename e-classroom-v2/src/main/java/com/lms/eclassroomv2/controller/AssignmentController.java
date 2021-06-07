package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Assignment;
import com.lms.eclassroomv2.model.dto.AssignmentDto;
import com.lms.eclassroomv2.services.AssignmentService;

@RestController
@RequestMapping(value = "api/assingments")
public class AssignmentController {

	@Autowired
	AssignmentService assignmentService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{assignmentId}")
	public Assignment getAssignmentById(@PathVariable Long assignmentId) {
		return assignmentService.getById(assignmentId);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/course/{courseId}")
	public List<Assignment> getAllAssignmentsForCourse(@PathVariable Long courseId) {
		return assignmentService.getAssignmentsForCourse(courseId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping
	public Assignment createNewAssignment(@RequestBody AssignmentDto assignmentDto) {
		return assignmentService.createAssignment(assignmentDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PutMapping("/{assignmentId}")
	public Assignment updateQuiz(@PathVariable Long assignmentId, @RequestBody AssignmentDto assignmentDto) {
		return assignmentService.updateAssignment(assignmentId, assignmentDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{assignmentId}")
	public void deleteQuiz(@PathVariable Long assignmentId) {
		assignmentService.deleteAssignment(assignmentId);
	}

}
