package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
	
	Optional<SchoolClass> findById(Long schoolClassId);
	
	SchoolClass findByName(String scName);
}
