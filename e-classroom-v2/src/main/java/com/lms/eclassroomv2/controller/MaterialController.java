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

import com.lms.eclassroomv2.model.Material;

import com.lms.eclassroomv2.model.dto.MaterialDto;
import com.lms.eclassroomv2.services.MaterialService;

@RestController
@RequestMapping(value = "api/materials")
public class MaterialController {

	@Autowired
	MaterialService materialService;

	@PreAuthorize("hasRole ('TEACHER')")
	@GetMapping()
	public List<Material> getAllMaterials() {
		return materialService.getAllMaterials();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{materialId}")
	public Material getMaterialById(@PathVariable Long materialId) {
		return materialService.getMaterialById(materialId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/course/{courseId}")
	public List<Material> getAllMaterialsForCourse(@PathVariable Long courseId) {
		return materialService.getAllMaterialsForCourse(courseId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping
	public ResponseEntity<?> addNewMaterial(@RequestBody MaterialDto materialDto) {
		return materialService.newMaterial(materialDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PutMapping("/{materialId}")
	public ResponseEntity<?> editMaterial(@PathVariable Long materialId, @RequestBody MaterialDto materialDto) {
		return materialService.editMaterial(materialId, materialDto);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{materialId}")
	public void deleteMaterial(@PathVariable Long materialId) {
		materialService.deleteMaterial(materialId);
	}
}
