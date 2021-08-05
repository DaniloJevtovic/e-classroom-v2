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
		// return postRepository.findByCourseId(courseId);
		// po datumu opadajuce - da najnovija objava bude prva
		return postRepository.findByCourseIdOrderByDateDesc(courseId);
	}

	public ResponseEntity<?> newPost(PostDto postDto) {
		try {
			Post post = new Post();
			post.setPost(postDto.getPost());
			post.setCourse(courseService.getCourseById(postDto.getCourseId()));
			post.setAuthor(userService.getUserById(postDto.getAuthorId()));
			post.setDate(new Timestamp(new Date().getTime()));

			/// return ResponseEntity.ok(postRepository.save(post));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", postRepository.save(post));
			res.put("message", "Post objavljen");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u objavi", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> updatePost(Long postId, PostDto postDto) {
		try {
			Post post = getPostById(postId);
			post.setPost(postDto.getPost());

			postRepository.save(post);

			return new ResponseEntity<>("Objave uspjesno izmjenjena", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u uzmjeni objave", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> deletePostById(Long postId) {
		try {
			postCommentService.deleteCommentsForPost(postId);
			postRepository.deleteById(postId);

			return ResponseEntity.ok("Objava uspjesno obrisana");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u brisanju objave", HttpStatus.BAD_REQUEST);
		}

	}
}
