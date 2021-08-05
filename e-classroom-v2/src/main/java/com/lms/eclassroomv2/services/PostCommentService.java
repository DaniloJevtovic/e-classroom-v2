package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<?> addNewComment(PostCommentDto postCommentDto) {

		try {
			PostComment postComment = new PostComment();
			postComment.setComment(postCommentDto.getComment());
			postComment.setPost(postService.getPostById(postCommentDto.getPostId()));
			postComment.setAuthor(userService.getUserById(postCommentDto.getAuthorId()));
			postComment.setDate(new Timestamp(new Date().getTime()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", postCommentRepository.save(postComment));
			res.put("message", "Komentar objavljen");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u komentaru", HttpStatus.BAD_REQUEST);
		}

	}

	public PostComment editComment(Long postComId, PostCommentDto postCommentDto) {
		PostComment postComment = getPostCommentById(postComId);
		postComment.setComment(postCommentDto.getComment());

		return postCommentRepository.save(postComment);
	}

	public ResponseEntity<?> deletePostCommentById(Long postCommentId) {
		try {
			postCommentRepository.deleteById(postCommentId);

			return ResponseEntity.ok("Komentar uspjesno obrisan");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce obrisati komentar", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteCommentsForPost(Long postId) {
		List<PostComment> comments = getAllPostCommentsForPost(postId);
		postCommentRepository.deleteAll(comments);
	}
}
