package com.lms.eclassroomv2.model.dto;

public class FileDto {

	private String name;
	private String url;
	private long size;
	private Long materialId;

	public FileDto(String name, String url, long size, Long materialId) {
		super();
		this.name = name;
		this.url = url;
		this.size = size;
		this.materialId = materialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

}
