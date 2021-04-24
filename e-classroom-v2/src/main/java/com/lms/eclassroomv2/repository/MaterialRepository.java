package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
	
	Optional<Material> findById(Long materialId);
	
	List<Material> findByCourseId(Long courseId);

}
