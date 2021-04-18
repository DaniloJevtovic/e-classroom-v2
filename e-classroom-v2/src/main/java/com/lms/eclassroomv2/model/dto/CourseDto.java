package com.lms.eclassroomv2.model.dto;

public class CourseDto {
	
	String name;
	String description;
	Long teacherId;
	Long schoolClassId;
	
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
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public Long getSchoolClassId() {
		return schoolClassId;
	}
	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}
}
