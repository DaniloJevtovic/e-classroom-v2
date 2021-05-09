package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.File;

public interface FileRepository extends JpaRepository<File, Long> {

	Optional<File> findById(Long fileId);
	
	List<File> findByMaterialId(Long materialId);
}
