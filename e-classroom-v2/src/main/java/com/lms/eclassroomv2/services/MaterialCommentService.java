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

import com.lms.eclassroomv2.model.MaterialComment;
import com.lms.eclassroomv2.model.dto.MaterialCommentDto;
import com.lms.eclassroomv2.repository.MaterialCommentRepository;

@Service
public class MaterialCommentService {

	@Autowired
	MaterialCommentRepository matCommentRepository;

	@Autowired
	MaterialService materialService;

	@Autowired
	UserService userService;

	public MaterialComment getMatComById(Long matComId) {
		return matCommentRepository.findById(matComId).orElse(null);
	}

	public List<MaterialComment> getAllCommentsForMaterial(Long matId) {
		return matCommentRepository.findByMaterialId(matId);
	}

	public ResponseEntity<?> newComment(MaterialCommentDto matComDto) {

		try {
			MaterialComment materialComment = new MaterialComment();
			materialComment.setComment(matComDto.getComment());
			materialComment.setMaterial(materialService.getMaterialById(matComDto.getMaterialId()));
			materialComment.setAuthor(userService.getUserById(matComDto.getAuthorId()));
			materialComment.setDate(new Timestamp(new Date().getTime()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", matCommentRepository.save(materialComment));
			res.put("message", "Komentar uspjesno dodat");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u dodavanju komentara", HttpStatus.BAD_REQUEST);
		}

	}

	public MaterialComment updateComment(Long matCommentId, MaterialCommentDto matComDto) {
		MaterialComment materialComment = getMatComById(matCommentId);
		materialComment.setComment(matComDto.getComment());

		return matCommentRepository.save(materialComment);
	}

	public void deleteMatComment(Long matComId) {
		matCommentRepository.deleteById(matComId);
	}

	// ako se bude brisao materijal da se obrisu i svi komentari vezani za njega
	public void deleteAllCommentsForMat(Long matId) {
		List<MaterialComment> matComments = getAllCommentsForMaterial(matId);
		matCommentRepository.deleteAll(matComments);
	}

}
