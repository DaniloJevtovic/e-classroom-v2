package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	Optional<Question> findById(Long questionId);
	
	List<Question> findByQuizId(Long quizId);

}
