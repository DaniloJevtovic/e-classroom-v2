package com.lms.eclassroomv2.model.dto;

public class StudentQuizResultDto {

	private int points;
	private Long studentId;
	private Long quizId;
	private double solveDuration;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public double getSolveDuration() {
		return solveDuration;
	}

	public void setSolveDuration(double solveDuration) {
		this.solveDuration = solveDuration;
	}

}
