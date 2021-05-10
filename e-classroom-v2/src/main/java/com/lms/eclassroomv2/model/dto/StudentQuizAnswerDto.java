package com.lms.eclassroomv2.model.dto;

public class StudentQuizAnswerDto {

	private Long questionId;
	private Long stQuizResId;
	private Long answerId;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getStQuizResId() {
		return stQuizResId;
	}

	public void setStQuizResId(Long stQuizResId) {
		this.stQuizResId = stQuizResId;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

}
