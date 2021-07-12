package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Post;
import com.lms.eclassroomv2.model.dto.PostDto;
import com.lms.eclassroomv2.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	@Autowired
	PostCommentService postCommentService;

	public Post getPostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public List<Post> getPostsForCourse(Long courseId) {
		return postRepository.findByCourseId(courseId);
	}

	public Post newPost(PostDto postDto) {
		Post post = new Post();
		post.setPost(postDto.getPost());
		post.setCourse(courseService.getCourseById(postDto.getCourseId()));
		post.setAuthor(userService.getUserById(postDto.getAuthorId()));
		post.setDate(new Timestamp(new Date().getTime()));

		return postRepository.save(post);
	}

	public Post updatePost(Long postId, PostDto postDto) {
		Post post = getPostById(postId);
		post.setPost(postDto.getPost());

		return postRepository.save(post);
	}

	public void deletePostById(Long postId) {
		postCommentService.deleteCommentsForPost(postId);
		postRepository.deleteById(postId);
	}
}
