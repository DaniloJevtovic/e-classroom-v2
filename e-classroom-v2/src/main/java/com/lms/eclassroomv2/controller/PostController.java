package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Post;

import com.lms.eclassroomv2.model.dto.PostDto;
import com.lms.eclassroomv2.services.PostService;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {

	@Autowired
	PostService postService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{postId}")
	public Post getPostById(@PathVariable Long postId) {
		return postService.getPostById(postId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/course/{courseId}")
	public List<Post> getAllPostsForCourse(@PathVariable Long courseId) {
		return postService.getPostsForCourse(courseId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PostMapping
	public Post addNewPost(@RequestBody PostDto postDto) {
		return postService.newPost(postDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PutMapping("/{postId}")
	public Post updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
		return postService.updatePost(postId, postDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deletePostById(postId);
	}
}
