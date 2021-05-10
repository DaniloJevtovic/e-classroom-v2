package com.lms.eclassroomv2.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lms.eclassroomv2.model.File;
import com.lms.eclassroomv2.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	FileRepository fileRepository;

	@Autowired
	MaterialService materialService;

	public List<File> getAllFiles() {
		return fileRepository.findAll();
	}

	public File getFileById(Long fileId) {
		return fileRepository.findById(fileId).orElse(null);
	}

	public List<File> getFilesForMaterial(Long materialId) {
		return fileRepository.findByMaterialId(materialId);
	}

	public File saveFile(MultipartFile multipartFile, Long materialId) throws IOException {

		File file = new File();
		file.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		file.setData(multipartFile.getBytes());

		String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path("sss")
				.toUriString();

		file.setUrl(fileUrl);

		file.setMaterial(materialService.getMaterialById(materialId));

		return fileRepository.save(file);
	}
	
	public void deleteFileById(Long fileId) {
		fileRepository.deleteById(fileId);
	}

	public void deleteFilesForMaterial(Long materialId) {
		List<File> materialFiles = getFilesForMaterial(materialId);
		fileRepository.deleteAll(materialFiles);
	}

}
