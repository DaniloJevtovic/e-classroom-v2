package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

	Optional<Assignment> findById(Long id);
	
	List<Assignment> findByCourseId(Long courseId);
}
