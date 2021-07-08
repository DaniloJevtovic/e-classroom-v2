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

import com.lms.eclassroomv2.model.Quiz;

import com.lms.eclassroomv2.model.dto.QuizDto;
import com.lms.eclassroomv2.services.QuizService;

@RestController
@RequestMapping(value = "api/quizzes")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PreAuthorize("hasRole ('TEACHER')")
	@GetMapping()
	public List<Quiz> getAllQuizzes() {
		return quizService.getAllQuizzes();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{quizId}")
	public Quiz getQuizById(@PathVariable Long quizId) {
		return quizService.getQuizById(quizId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/course/{courseId}")
	public List<Quiz> getAllQuizzesForCourse(@PathVariable Long courseId) {
		return quizService.getQuizzesForCourse(courseId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping
	public ResponseEntity<?> addNewQuiz(@RequestBody QuizDto quizDto) {
		return quizService.newQuiz(quizDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PutMapping("/{quizId}")
	public Quiz updateQuiz(@PathVariable Long quizId, @RequestBody QuizDto quizDto) {
		return quizService.updateQuiz(quizId, quizDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable Long quizId) {
		quizService.deleteQuiz(quizId);
	}

}
