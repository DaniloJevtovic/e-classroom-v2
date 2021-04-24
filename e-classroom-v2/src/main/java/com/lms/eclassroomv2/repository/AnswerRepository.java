package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	Optional<Answer> findById(Long answerid);
	
	List<Answer> findByQuestionId(Long questionId);

}
