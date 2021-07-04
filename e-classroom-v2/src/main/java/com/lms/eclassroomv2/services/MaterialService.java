package com.lms.eclassroomv2.services;

import java.util.List;

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

			// kad se postavi novi materijal da se na zid postavi objava
			Post post = new Post();
			post.setCourse(courseService.getCourseById(materialDto.getCourseId()));
			post.setPost("Postavljen je novi materijal: " + material.getName());

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = ((UserDetails) principal).getUsername();

			post.setAuthor(userService.getUserByUsername(username));
			postRepository.save(post);

			materialRepository.save(material);

			return new ResponseEntity<>("Materijala uspjesno kreiran", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Greska u kreiranju materijala", HttpStatus.BAD_REQUEST);
		}

	}

	public Material editMaterial(Long materialId, MaterialDto materialDto) {
		Material material = getMaterialById(materialId);
		material.setName(materialDto.getName());
		material.setDescription(materialDto.getDescription());
		// material.setCourse(courseService.getCourseById(materialDto.getCourseId()));

		return materialRepository.save(material);
	}

	public void deleteMaterial(Long materialId) {
		fileService.deleteFilesForMaterial(materialId);
		matCommentService.deleteAllCommentsForMat(materialId);
		materialRepository.deleteById(materialId);
	}

}
