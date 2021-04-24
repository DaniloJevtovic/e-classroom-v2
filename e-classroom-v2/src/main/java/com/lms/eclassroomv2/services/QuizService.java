package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Quiz;
import com.lms.eclassroomv2.model.dto.QuizDto;
import com.lms.eclassroomv2.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	CourseService courseService;

	public List<Quiz> getAllQuizzes() {
		return quizRepository.findAll();
	}

	public Quiz getQuizById(Long quizId) {
		return quizRepository.findById(quizId).orElse(null);
	}

	public List<Quiz> getQuizzesForCourse(Long courseId) {
		return quizRepository.findByCourseId(courseId);
	}

	public Quiz newQuiz(QuizDto quizDto) {
		Quiz quiz = new Quiz();
		quiz.setName(quizDto.getName());
		quiz.setInstructions(quizDto.getInstructions());
		quiz.setDuration(quizDto.getDuration());
		quiz.setCourse(courseService.getCourseById(quizDto.getCourseId()));

		return quizRepository.save(quiz);
	}

}
