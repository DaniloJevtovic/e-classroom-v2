package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<?> addNewAnswer(AnswerDto answerDto) {
		try {
			Answer answer = new Answer();
			answer.setAnswer(answerDto.getAnswer());
			answer.setCorrect(answerDto.isCorrect());
			answer.setQuestion(questionService.getQuestionById(answerDto.getQuestionId()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", answerRepository.save(answer));
			res.put("message", "Odogovor uspjesno dodat");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u dodavanju odgovora", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> updateAnswer(Long answerId, AnswerDto answerDto) {
		try {
			Answer answer = getAnswerById(answerId);
			answer.setAnswer(answerDto.getAnswer());
			answer.setCorrect(answerDto.isCorrect());
			answerRepository.save(answer);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", answerRepository.save(answer));
			res.put("message", "Odogovor uspjesno izmjenjen");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u azuriranju odgovora", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteAnswer(Long answerId) {
		answerRepository.deleteById(answerId);
	}

	public void deleteAllAnswersForQuestion(Long questionId) {
		List<Answer> questionAnswers = getAnswersForQuestion(questionId);
		answerRepository.deleteAll(questionAnswers);
	}
}
