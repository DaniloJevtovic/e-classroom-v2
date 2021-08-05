package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.PostComment;
import com.lms.eclassroomv2.model.dto.PostCommentDto;
import com.lms.eclassroomv2.services.PostCommentService;

@RestController
@RequestMapping(value = "api/postComents")
public class PostCommentController {

	@Autowired
	PostCommentService commentService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{commentId}")
	public PostComment getCommetnById(@PathVariable Long commentId) {
		return commentService.getPostCommentById(commentId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/post/{postId}")
	public List<PostComment> getAllCommentsForPost(@PathVariable Long postId) {
		return commentService.getAllPostCommentsForPost(postId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PostMapping
	public ResponseEntity<?> addNewComment(@RequestBody PostCommentDto commentDto) {
		return commentService.addNewComment(commentDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PutMapping("/{postComId}")
	public PostComment editComment(@PathVariable Long postComId, @RequestBody PostCommentDto commentDto) {
		return commentService.editComment(postComId, commentDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
		return commentService.deletePostCommentById(commentId);
	}
}
