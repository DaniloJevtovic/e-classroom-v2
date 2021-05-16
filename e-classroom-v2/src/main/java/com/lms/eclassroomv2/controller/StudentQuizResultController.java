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

import com.lms.eclassroomv2.model.StudentQuizResult;
import com.lms.eclassroomv2.model.dto.StudentQuizResultDto;
import com.lms.eclassroomv2.services.StudentQuizResultService;

@RestController
@RequestMapping(value = "api/results")
public class StudentQuizResultController {

	@Autowired
	StudentQuizResultService stQuizResultService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{resId}")
	public StudentQuizResult getResultById(@PathVariable Long resId) {
		return stQuizResultService.getStQuizResById(resId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/student/{studentId}")
	public List<StudentQuizResult> getAllResultsForStudent(@PathVariable Long studentId) {
		return stQuizResultService.getAllResultsForStudent(studentId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/student/{studentId}/quiz/{quizId}")
	public StudentQuizResult getResultForStudentAndQuiz(@PathVariable Long studentId, @PathVariable Long quizId) {
		return stQuizResultService.getResultForStudentAndQuiz(studentId, quizId);
	}

	@PreAuthorize("hasRole ('STUDENT')")
	@PostMapping
	public StudentQuizResult createStudentQuizResult(@RequestBody StudentQuizResultDto stQuizResDto) {
		return stQuizResultService.saveResult(stQuizResDto);
	}
}
