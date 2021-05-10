package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentQuizAnswer;

public interface StudentQuizAnswerRepository extends JpaRepository<StudentQuizAnswer, Long> {

	Optional<StudentQuizAnswer> findById(Long stQuizAnsId);
	
	//odgovori za odredjeni rezultat kviza - svi odgovori
	List<StudentQuizAnswer> findByStudentQuizResultId(Long stQuizResId);
	
	//odgovori za odredjeni rezultat i odredjeno pitanje
	List<StudentQuizAnswer> findByStudentQuizResultIdAndQuestionId(Long stQuizResId, Long questionId);
}
