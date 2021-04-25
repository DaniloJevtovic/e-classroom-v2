package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Answer;
import com.lms.eclassroomv2.model.dto.AnswerDto;
import com.lms.eclassroomv2.repository.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionService questionService;
	
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}
	
	public Answer getAnswerById(Long answerId) {
		return answerRepository.findById(answerId).orElse(null);
	}
	
	public List<Answer> getAnswersForQuestion(Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}
	
	public Answer addNewAnswer (AnswerDto answerDto) {
		Answer answer = new Answer();
		answer.setAnswer(answerDto.getAnswer());
		answer.setCorrect(answerDto.isCorrect());
		answer.setQuestion(questionService.getQuestionById(answerDto.getQuestionId()));
		return answerRepository.save(answer);
	}
}
