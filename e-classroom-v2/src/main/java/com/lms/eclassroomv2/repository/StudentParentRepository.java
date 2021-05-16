package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentParent;

public interface StudentParentRepository extends JpaRepository<StudentParent, Long> {
	
	Optional<StudentParent> findById(Long stParentId);

}
