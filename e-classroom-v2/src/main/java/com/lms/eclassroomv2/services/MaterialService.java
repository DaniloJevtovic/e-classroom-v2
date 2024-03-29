package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Material;
import com.lms.eclassroomv2.model.Post;
import com.lms.eclassroomv2.model.dto.MaterialDto;
import com.lms.eclassroomv2.repository.MaterialRepository;
import com.lms.eclassroomv2.repository.PostRepository;

@Service
public class MaterialService {

	@Autowired
	MaterialRepository materialRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	FileService fileService;

	@Autowired
	PostService postService;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserService userService;

	@Autowired
	MaterialCommentService matCommentService;

	public List<Material> getAllMaterials() {
		return materialRepository.findAll();
	}

	public Material getMaterialById(Long materialId) {
		return materialRepository.findById(materialId).orElse(null);
	}

	public List<Material> getAllMaterialsForCourse(Long courseId) {
		return materialRepository.findByCourseId(courseId);
	}

	public ResponseEntity<?> newMaterial(MaterialDto materialDto) {

		try {
			Material material = new Material();
			material.setName(materialDto.getName());
			material.setDescription(materialDto.getDescription());
			material.setCourse(courseService.getCourseById(materialDto.getCourseId()));
			material.setCreationDate(new Timestamp(new Date().getTime()));

			// kad se postavi novi materijal da se na zid postavi objava
			Post post = new Post();
			post.setCourse(courseService.getCourseById(materialDto.getCourseId()));
			post.setPost("Postavljen je novi materijal: " + material.getName());

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails) principal).getUsername();

			post.setAuthor(userService.getUserByUsername(username));
			post.setDate(new Timestamp(new Date().getTime()));
			postRepository.save(post);

			// materialRepository.save(material);
			// return new ResponseEntity<>("Materijala uspjesno kreiran", HttpStatus.OK);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", materialRepository.save(material));
			res.put("message", "Materijal uspjesno kreiran");

			return ResponseEntity.ok(res);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska u kreiranju materijala", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> editMaterial(Long materialId, MaterialDto materialDto) {
		try {
			Material material = getMaterialById(materialId);
			material.setName(materialDto.getName());
			material.setDescription(materialDto.getDescription());
			// material.setCourse(courseService.getCourseById(materialDto.getCourseId()));

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", materialRepository.save(material));
			res.put("message", "Materijal uspjesno azuriran");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Greska u azuriranju materijala", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteMaterial(Long materialId) {
		fileService.deleteFilesForMaterial(materialId);
		matCommentService.deleteAllCommentsForMat(materialId);
		materialRepository.deleteById(materialId);
	}

}
