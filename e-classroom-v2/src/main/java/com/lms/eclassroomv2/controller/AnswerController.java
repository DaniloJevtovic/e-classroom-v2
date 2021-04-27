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

import com.lms.eclassroomv2.model.Answer;
import com.lms.eclassroomv2.model.dto.AnswerDto;
import com.lms.eclassroomv2.services.AnswerService;

@RestController
@RequestMapping(value = "api/answers")
public class AnswerController {

	@Autowired
	AnswerService answerService;

	@PreAuthorize("hasRole ('TEACHER')")
	@GetMapping()
	public List<Answer> getAllAnswers() {
		return answerService.getAllAnswers();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{answerId}")
	public Answer getAnswerById(@PathVariable Long answerId) {
		return answerService.getAnswerById(answerId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/question/{questionId}")
	public List<Answer> getAnswersForQuestion(@PathVariable Long questionId) {
		return answerService.getAnswersForQuestion(questionId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping
	public Answer addNewAnswer(@RequestBody AnswerDto answerDto) {
		return answerService.addNewAnswer(answerDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PutMapping("/{answerId}")
	public Answer updateAnswer(@PathVariable Long answerId, @RequestBody AnswerDto answerDto) {
		return answerService.updateAnswer(answerId, answerDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{answerId}")
	public void deleteAnswer(@PathVariable Long answerId) {
		answerService.deleteAnswer(answerId);
	}

}
