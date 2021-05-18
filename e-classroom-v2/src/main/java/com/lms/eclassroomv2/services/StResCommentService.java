package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public StudentResultComment getStResComById(Long resComId) {
		return stResCommentRepository.findById(resComId).orElse(null);
	}

	public List<StudentResultComment> getCommentsForResult(Long stResId) {
		return stResCommentRepository.findByStudentResultId(stResId);
	}

	public StudentResultComment addNewResComment(StudentResultCommentDto stResComDto) {
		StudentResultComment stResComment = new StudentResultComment();
		stResComment.setComment(stResComDto.getComment());
		stResComment.setStudentResult(stResultService.getStQuizResById(stResComDto.getResultId()));

		return stResCommentRepository.save(stResComment);
	}
}
