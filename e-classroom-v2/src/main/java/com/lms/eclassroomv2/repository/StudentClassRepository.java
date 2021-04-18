package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

	Optional<StudentClass> findById(Long studentClassId);
	
	List<StudentClass> findBySchoolClassId(Long scClassId);
}
