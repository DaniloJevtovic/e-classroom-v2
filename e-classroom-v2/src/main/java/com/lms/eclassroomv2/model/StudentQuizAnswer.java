package com.lms.eclassroomv2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_quiz_answers")
public class StudentQuizAnswer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	Question question;
	
	@ManyToOne
	StudentQuizResult studentQuizResult;
	
	@OneToOne
	Answer answer;

	public StudentQuizAnswer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public StudentQuizResult getStudentQuizResult() {
		return studentQuizResult;
	}

	public void setStudentQuizResult(StudentQuizResult studentQuizResult) {
		this.studentQuizResult = studentQuizResult;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
