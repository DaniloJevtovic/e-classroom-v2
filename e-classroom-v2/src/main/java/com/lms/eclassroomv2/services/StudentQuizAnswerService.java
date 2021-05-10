package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	// svi odgovori za rezultat (jednog ucenika)
	public List<StudentQuizAnswer> getAnswersByStQuizRes(Long stQuizResId) {
		return stQuizAnswerRepository.findByStudentQuizResultId(stQuizResId);
	}

	// lista odgovora za odrenji rezultat i odredjeno pitanje
	public List<StudentQuizAnswer> getAnsByStQuizResAndQuestionId(Long stQuizResId, Long questionId) {
		return stQuizAnswerRepository.findByStudentQuizResultIdAndQuestionId(stQuizResId, questionId);
	}

	public StudentQuizAnswer saveAnswer(StudentQuizAnswerDto stQuizAnsDto) {
		StudentQuizAnswer stQuizAnswer = new StudentQuizAnswer();
		stQuizAnswer.setStudentQuizResult(studentQuizResultService.getStQuizResById(stQuizAnsDto.getStQuizResId()));
		stQuizAnswer.setQuestion(questionService.getQuestionById(stQuizAnsDto.getQuestionId()));
		stQuizAnswer.setAnswer(answerService.getAnswerById(stQuizAnsDto.getAnswerId()));

		// update poena
		updatePointsToResultSelect(stQuizAnsDto.getStQuizResId(),
				answerService.getAnswerById(stQuizAnsDto.getAnswerId()));

		return stQuizAnswerRepository.save(stQuizAnswer);
	}

	public void deleteAnswer(Long stQuizAnsId) {
		StudentQuizAnswer studentQuizAnswer = getStQuizAnswerById(stQuizAnsId);
		Long stQuizResId = studentQuizAnswer.getStudentQuizResult().getId();
		Answer answer = studentQuizAnswer.getAnswer();

		updatePointsToResultDeselct(stQuizResId, answer);

		stQuizAnswerRepository.deleteById(stQuizAnsId);

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
