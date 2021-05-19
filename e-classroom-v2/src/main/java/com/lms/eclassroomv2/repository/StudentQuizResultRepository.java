package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentQuizResult;

public interface StudentQuizResultRepository extends JpaRepository<StudentQuizResult, Long> {

	Optional<StudentQuizResult> findById(Long id);

	// svi rezultati za ucenika
	List<StudentQuizResult> findByStudentId(Long studentId);

	// rezultati za odredjeni kviz i odrejenog ucenika
	StudentQuizResult findByStudentIdAndQuizId(Long studentId, Long quizId);
	
	//svi rezultati za odredjeni kviz - da profesor vidi rezultate tog kviza
	List<StudentQuizResult> findByQuizId(Long quizId);

}
