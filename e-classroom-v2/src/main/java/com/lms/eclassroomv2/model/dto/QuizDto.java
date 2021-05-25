package com.lms.eclassroomv2.model.dto;

public class QuizDto {

	private String name;
	private String instructions;
	private int duration;
	private Long courseId;
	private String quizStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getQuizStatus() {
		return quizStatus;
	}

	public void setQuizStatus(String quizStatus) {
		this.quizStatus = quizStatus;
	}

}
