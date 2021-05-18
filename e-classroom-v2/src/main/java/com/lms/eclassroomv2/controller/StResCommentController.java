package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.StudentResultComment;
import com.lms.eclassroomv2.model.dto.StudentResultCommentDto;
import com.lms.eclassroomv2.services.StResCommentService;

@RestController
@RequestMapping("api/stResComms")
public class StResCommentController {

	@Autowired
	StResCommentService stResCommentService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{resComId}")
	public StudentResultComment getResCommById(@PathVariable Long resComId) {
		return stResCommentService.getStResComById(resComId);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/result/{resId}")
	public List<StudentResultComment> getCommentsForRes(@PathVariable Long resId) {
		return stResCommentService.getCommentsForResult(resId);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@PostMapping
	public StudentResultComment addResComment(@RequestBody StudentResultCommentDto stResComDto) {
		return stResCommentService.addNewResComment(stResComDto);
	}

}
