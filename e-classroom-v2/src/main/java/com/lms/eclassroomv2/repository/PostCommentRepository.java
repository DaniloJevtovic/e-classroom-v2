package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>{
	
	Optional<PostComment> findById(Long postComId);
	
	List<PostComment> findByPostId(Long postId);

}
