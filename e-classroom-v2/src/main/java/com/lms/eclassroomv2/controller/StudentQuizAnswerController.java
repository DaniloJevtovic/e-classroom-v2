package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.StudentQuizAnswer;
import com.lms.eclassroomv2.model.dto.StudentQuizAnswerDto;
import com.lms.eclassroomv2.services.StudentQuizAnswerService;

@RestController
@RequestMapping(value = "api/stAnswers")
public class StudentQuizAnswerController {

	@Autowired
	StudentQuizAnswerService stQuizAnswerService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{stAnsId}")
	public StudentQuizAnswer getStAnswerById(@PathVariable Long stAnsId) {
		return stQuizAnswerService.getStQuizAnswerById(stAnsId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/stQuizRes/{stQuizResId}")
	public List<StudentQuizAnswer> getAllStQuizResAnswers(@PathVariable Long stQuizResId) {
		return stQuizAnswerService.getAnswersByStQuizRes(stQuizResId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/stQuizRes/{stQuizResId}/question/{questId}")
	public List<StudentQuizAnswer> getAnsForStQuizResAndQuestion(@PathVariable Long stQuizResId,
			@PathVariable Long questId) {
		return stQuizAnswerService.getAnsByStQuizResAndQuestionId(stQuizResId, questId);
	}

	@PreAuthorize("hasRole ('STUDENT')")
	@PostMapping
	public StudentQuizAnswer addStudentAnswer(@RequestBody StudentQuizAnswerDto stQuizAnsDto) {
		return stQuizAnswerService.saveAnswer(stQuizAnsDto);
	}

	@PreAuthorize("hasRole ('STUDENT')")
	@DeleteMapping("/res/{stResId}/ans/{ansId}") 
	public void deleteStudentAnswer(@PathVariable Long stResId, @PathVariable Long ansId) {
		stQuizAnswerService.deleteAnswer(stResId, ansId);
	}

}
