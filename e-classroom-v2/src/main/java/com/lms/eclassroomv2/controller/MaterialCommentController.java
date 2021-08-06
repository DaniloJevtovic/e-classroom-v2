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

import com.lms.eclassroomv2.model.MaterialComment;
import com.lms.eclassroomv2.model.dto.MaterialCommentDto;
import com.lms.eclassroomv2.services.MaterialCommentService;

@RestController
@RequestMapping(value = "api/matComments")
public class MaterialCommentController {

	@Autowired
	MaterialCommentService matCommService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{matComId}")
	public MaterialComment getCommentById(@PathVariable Long matComId) {
		return matCommService.getMatComById(matComId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/material/{matId}")
	public List<MaterialComment> getAllCommentsForMaterial(@PathVariable Long matId) {
		return matCommService.getAllCommentsForMaterial(matId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PostMapping
	public ResponseEntity<?> addNewComment(@RequestBody MaterialCommentDto matComDto) {
		return matCommService.newComment(matComDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PutMapping("/{matComId}")
	public MaterialComment editComment(@PathVariable Long matComId, @RequestBody MaterialCommentDto matComDto) {
		return matCommService.updateComment(matComId, matComDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@DeleteMapping("/{matComId}")
	public void deleteComment(@PathVariable Long matComId) {
		matCommService.deleteMatComment(matComId);
	}

}
