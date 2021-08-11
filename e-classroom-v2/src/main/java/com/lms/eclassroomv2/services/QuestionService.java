package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Question;
import com.lms.eclassroomv2.model.QuestionType;
import com.lms.eclassroomv2.model.dto.QuestionDto;
import com.lms.eclassroomv2.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizService quizService;

	@Autowired
	AnswerService answerService;

	public List<Question> getAllQuestions() {
		return questionRepository.findAll();
	}

	public Question getQuestionById(Long questionId) {
		return questionRepository.findById(questionId).orElse(null);
	}

	public List<Question> getQuestionsForQuiz(Long quizId) {
		return questionRepository.findByQuizId(quizId);
	}

	public ResponseEntity<?> addNewQuestion(QuestionDto questionDto) {
		try {
			Question question = new Question();
			question.setQuestion(questionDto.getQuestion());
			question.setPoints(questionDto.getPoints());

			question.setQuiz(quizService.getQuizById(questionDto.getQuizId()));
			question.setQuestionType(QuestionType.valueOf(questionDto.getQuestionType()));

			quizService.updatePointsToQuiz(questionDto.getQuizId(), questionDto.getPoints());

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", questionRepository.save(question));
			res.put("message", "Pitanje uspjesno dodato");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u kreiranju pitanja", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> updateQuestion(Long questionId, QuestionDto questionDto) {
		try {
			Question question = getQuestionById(questionId);
			question.setQuestion(questionDto.getQuestion());
			question.setPoints(questionDto.getPoints());
			question.setQuestionType(QuestionType.valueOf(questionDto.getQuestionType()));

			quizService.updatePointsToQuiz(question.getQuiz().getId(), questionDto.getPoints());

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", questionRepository.save(question));
			res.put("message", "Pitanje uspjesno izmjenjeno");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u izmjeni pitanja", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteQuestion(Long questionId) {
		answerService.deleteAllAnswersForQuestion(questionId);
		questionRepository.deleteById(questionId);
	}
}
