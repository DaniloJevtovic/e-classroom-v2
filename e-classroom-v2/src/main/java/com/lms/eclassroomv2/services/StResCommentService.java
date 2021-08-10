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

import com.lms.eclassroomv2.model.StudentResultComment;
import com.lms.eclassroomv2.model.dto.StudentResultCommentDto;
import com.lms.eclassroomv2.repository.StudentResultCommentRepository;

@Service
public class StResCommentService {

	@Autowired
	StudentResultCommentRepository stResCommentRepository;

	@Autowired
	StudentQuizResultService stResultService;

	@Autowired
	UserService userService;

	public StudentResultComment getStResComById(Long resComId) {
		return stResCommentRepository.findById(resComId).orElse(null);
	}

	public List<StudentResultComment> getCommentsForResult(Long stResId) {
		return stResCommentRepository.findByStudentResultId(stResId);
	}

	public ResponseEntity<?> addNewResComment(StudentResultCommentDto stResComDto) {
		try {
			StudentResultComment stResComment = new StudentResultComment();
			stResComment.setComment(stResComDto.getComment());
			stResComment.setStudentResult(stResultService.getStQuizResById(stResComDto.getResultId()));
			stResComment.setAuthor(userService.getUserById(stResComDto.getAuthorId()));
			stResComment.setDate(new Timestamp(new Date().getTime()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", stResCommentRepository.save(stResComment));
			res.put("message", "Komentar uspjesno objavljen");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u dodavanju komentara", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteResCommById(Long resCommId) {
		stResCommentRepository.deleteById(resCommId);
	}
}
