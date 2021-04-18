package com.lms.eclassroomv2.model.dto;

public class StudentClassDto {

	private String name;
	private String description;
	private Long scClassId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getScClassId() {
		return scClassId;
	}
	public void setScClassId(Long scClassId) {
		this.scClassId = scClassId;
	}
}
