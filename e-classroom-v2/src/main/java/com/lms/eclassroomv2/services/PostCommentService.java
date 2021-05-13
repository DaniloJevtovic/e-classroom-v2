package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.PostComment;
import com.lms.eclassroomv2.model.dto.PostCommentDto;
import com.lms.eclassroomv2.repository.PostCommentRepository;

@Service
public class PostCommentService {

	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;

	public PostComment getPostCommentById(Long postComId) {
		return postCommentRepository.findById(postComId).orElse(null);
	}
	
	public List<PostComment> getAllPostCommentsForPost(Long postId) {
		return postCommentRepository.findByPostId(postId);
	}
	
	public PostComment addNewComment(PostCommentDto postCommentDto) {
		PostComment postComment = new PostComment();
		postComment.setComment(postCommentDto.getComment());
		postComment.setPost(postService.getPostById(postCommentDto.getPostId()));
		postComment.setAuthor(userService.getUserById(postCommentDto.getAuthorId()));
		
		return postCommentRepository.save(postComment);
	}
	
	public void deletePostCommentById(Long postCommentId) {
		postCommentRepository.deleteById(postCommentId);
	}
	
	public void deleteCommentsForPost(Long postId) {
		List<PostComment> comments = getAllPostCommentsForPost(postId);
		postCommentRepository.deleteAll(comments);
	}
}
