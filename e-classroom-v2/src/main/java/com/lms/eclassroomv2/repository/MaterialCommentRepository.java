package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.MaterialComment;

public interface MaterialCommentRepository extends JpaRepository<MaterialComment, Long> {

	public Optional<MaterialComment> findById(Long matComId);

	public List<MaterialComment> findByMaterialId(Long matId);

}
