package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Answer;
import com.lms.eclassroomv2.model.StudentQuizAnswer;
import com.lms.eclassroomv2.model.dto.StudentQuizAnswerDto;
import com.lms.eclassroomv2.repository.StudentQuizAnswerRepository;

@Service
public class StudentQuizAnswerService {

	@Autowired
	StudentQuizAnswerRepository stQuizAnswerRepository;

	@Autowired
	StudentQuizResultService studentQuizResultService;

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	// svi odgovori, svi studenti
	public List<StudentQuizAnswer> getAllStudentQuizAnswers() {
		return stQuizAnswerRepository.findAll();
	}

	public StudentQuizAnswer getStQuizAnswerById(Long stQuizAnsId) {
		return stQuizAnswerRepository.findById(stQuizAnsId).orElse(null);
	}

	// za brisanje - id rezultata iz kojeg se brise i id pitanja
	public StudentQuizAnswer getStQuizAnswerByResultIdAndAnswerId(Long resId, Long answId) {
		return stQuizAnswerRepository.findByStudentQuizResultIdAndAnswerId(resId, answId);
	}

	// svi odgovori za rezultat (jednog ucenika)
	public List<StudentQuizAnswer> getAnswersByStQuizRes(Long stQuizResId) {
		return stQuizAnswerRepository.findByStudentQuizResultId(stQuizResId);
	}

	// lista odgovora za odrenji rezultat i odredjeno pitanje
	public List<StudentQuizAnswer> getAnsByStQuizResAndQuestionId(Long stQuizResId, Long questionId) {
		return stQuizAnswerRepository.findByStudentQuizResultIdAndQuestionId(stQuizResId, questionId);
	}

	public ResponseEntity<?> saveAnswer(StudentQuizAnswerDto stQuizAnsDto) {

		try {
			StudentQuizAnswer stQuizAnswer = new StudentQuizAnswer();
			stQuizAnswer.setStudentQuizResult(studentQuizResultService.getStQuizResById(stQuizAnsDto.getStQuizResId()));
			stQuizAnswer.setQuestion(questionService.getQuestionById(stQuizAnsDto.getQuestionId()));
			stQuizAnswer.setAnswer(answerService.getAnswerById(stQuizAnsDto.getAnswerId()));

			// update poena
			updatePointsToResultSelect(stQuizAnsDto.getStQuizResId(),
					answerService.getAnswerById(stQuizAnsDto.getAnswerId()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", stQuizAnswerRepository.save(stQuizAnswer));
			res.put("message", "Odgovor uspjesno sacuvan");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u cuvanju odgovora", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteAnswer(Long stResId, Long ansId) {
		StudentQuizAnswer studentQuizAnswer = getStQuizAnswerByResultIdAndAnswerId(stResId, ansId);
		// Answer answer = studentQuizAnswer.getAnswer();

		Answer answer = answerService.getAnswerById(ansId);

		updatePointsToResultDeselct(stResId, answer);

		stQuizAnswerRepository.deleteById(studentQuizAnswer.getId());
	}

	// f-ja za update poena u rezultatima kad ucenik oznaci odgovor
	public void updatePointsToResultSelect(Long stQuizResId, Answer answer) {
		int points = studentQuizResultService.getStQuizResById(stQuizResId).getPoints();

		if (answer.isCorrect()) {
			points += answer.getQuestion().getPoints();

		} else {
			points -= answer.getQuestion().getPoints();
		}

		studentQuizResultService.updateResult(stQuizResId, points);
	}

	// f-ja za update poena u rezultatima kad ucenik oznaci odgovor
	public void updatePointsToResultDeselct(Long stQuizResId, Answer answer) {
		int points = studentQuizResultService.getStQuizResById(stQuizResId).getPoints();

		if (answer.isCorrect()) {
			points -= answer.getQuestion().getPoints();

		} else {
			points += answer.getQuestion().getPoints();
		}

		studentQuizResultService.updateResult(stQuizResId, points);
	}

}
