package com.lms.eclassroomv2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lms.eclassroomv2.model.File;
import com.lms.eclassroomv2.services.FileService;

@RestController
@RequestMapping(value = "api/files")
public class FileController {

	@Autowired
	FileService fileService;

	@PreAuthorize("hasRole ('TEACHER')")
	@GetMapping()
	public List<File> getAllFiles() {
		return fileService.getAllFiles();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{fileId}")
	public File getFileById(@PathVariable Long fileId) {
		return fileService.getFileById(fileId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/material/{materialId}")
	public List<File> getFilesForMaterial(@PathVariable Long materialId) {
		return fileService.getFilesForMaterial(materialId);
	}

	@PreAuthorize("hasRole ('TEACHER')")
	@PostMapping("/material/{materialId}")
	public File uploadFile(@RequestBody MultipartFile file, @PathVariable Long materialId) throws IOException {
		return fileService.saveFile(file, materialId);
	}

	@GetMapping("/downloadFile/{fileId}")
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
		File file = fileService.getFileById(fileId);

		return ResponseEntity.ok()
				// .contentType(MediaType.parseMediaType(file.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(new ByteArrayResource(file.getData()));
	}
	
	@PreAuthorize("hasRole ('TEACHER')")
	@DeleteMapping("/{fileId}")
	public void deleteFile(@PathVariable Long fileId) {
		fileService.deleteFileById(fileId);
	}
}
