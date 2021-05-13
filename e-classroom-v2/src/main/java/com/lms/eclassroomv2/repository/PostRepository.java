package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<Post> findById(Long postId);
	
	List<Post> findByCourseId(Long courseId);
	
}
