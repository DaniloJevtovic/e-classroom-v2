package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Question addNewQuestion(QuestionDto questionDto) {
		Question question = new Question();
		question.setQuestion(questionDto.getQuestion());
		question.setPoints(questionDto.getPoints());
		question.setQuiz(quizService.getQuizById(questionDto.getQuizId()));
		question.setQuestionType(QuestionType.valueOf(questionDto.getQuestionType()));

		return questionRepository.save(question);
	}

	public Question updateQuestion(Long questionId, QuestionDto questionDto) {
		Question question = getQuestionById(questionId);
		question.setQuestion(questionDto.getQuestion());
		question.setPoints(questionDto.getPoints());
		question.setQuestionType(QuestionType.valueOf(questionDto.getQuestionType()));

		return questionRepository.save(question);
	}

	public void deleteQuestion(Long questionId) {
		answerService.deleteAllAnswersForQuestion(questionId);
		questionRepository.deleteById(questionId);
	}
}
