package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	Optional<Quiz> findById(Long quizId);
	
	List<Quiz> findByCourseId(Long courseId);
}
