package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Assignment;
import com.lms.eclassroomv2.model.dto.AssignmentDto;
import com.lms.eclassroomv2.repository.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	CourseService courseService;

	public Assignment getById(Long assignmentId) {
		return assignmentRepository.findById(assignmentId).orElse(null);
	}

	public List<Assignment> getAssignmentsForCourse(Long courseId) {
		return assignmentRepository.findByCourseId(courseId);
	}

	public Assignment createAssignment(AssignmentDto assignmentDto) {
		Assignment assignment = new Assignment();
		assignment.setName(assignmentDto.getName());
		assignment.setInstructions(assignmentDto.getInstructions());
		assignment.setPoints(assignmentDto.getPoints());
		assignment.setCourse(courseService.getCourseById(assignmentDto.getCourseId()));

		return assignmentRepository.save(assignment);
	}

	public Assignment updateAssignment(Long assignmentId, AssignmentDto assignmentDto) {
		Assignment assignment = getById(assignmentId);
		assignment.setName(assignmentDto.getName());
		assignment.setInstructions(assignmentDto.getInstructions());
		assignment.setPoints(assignmentDto.getPoints());
		//assignment.setCourse(courseService.getCourseById(assignmentDto.getCourseId()));

		return assignmentRepository.save(assignment);
	}
	
	public void deleteAssignment(Long assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}

}
