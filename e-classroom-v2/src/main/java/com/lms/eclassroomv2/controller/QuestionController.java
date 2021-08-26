package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Question;
import com.lms.eclassroomv2.model.dto.QuestionDto;
import com.lms.eclassroomv2.services.QuestionService;

@RestController
@RequestMapping(value = "api/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PreAuthorize("hasRole ('TEACHER')")
	@GetMapping()
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{questionId}")
	public Question getQuestionById(@PathVariable Long questionId) {
		return questionService.getQuestionById(questionId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/quiz/{quizId}")
	public List<Question> getAllQuestionsForQuiz(@PathVariable Long quizId) {
		return questionService.getQuestionsForQuiz(quizId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping
	public ResponseEntity<?> addNewQuestion(@RequestBody QuestionDto questionDto) {
		return questionService.addNewQuestion(questionDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PutMapping("/{questionId}")
	public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {
		return questionService.updateQuestion(questionId, questionDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		return questionService.deleteQuestion(questionId);
	}
}
