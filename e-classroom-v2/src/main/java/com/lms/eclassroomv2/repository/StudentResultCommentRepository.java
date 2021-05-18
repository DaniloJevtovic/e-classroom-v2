package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentResultComment;

public interface StudentResultCommentRepository extends JpaRepository<StudentResultComment, Long> {
	
	Optional<StudentResultComment> findById(Long resComId);
	
	List<StudentResultComment> findByStudentResultId(Long resId);

}
