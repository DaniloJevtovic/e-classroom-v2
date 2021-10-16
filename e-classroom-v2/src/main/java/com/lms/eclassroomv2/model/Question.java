package com.lms.eclassroomv2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "question")
	private String question;

	@Column(name = "points")
	private int points;
	
	// broj odgovora za pitanja
	@Column(name = "num_of_ans")
	private int numOfAns;

	@Column(name = "question_type")
	@Enumerated(EnumType.ORDINAL)
	QuestionType questionType;

	@ManyToOne
	private Quiz quiz;

	public Question() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public int getNumOfAns() {
		return numOfAns;
	}

	public void setNumOfAns(int numOfAns) {
		this.numOfAns = numOfAns;
	}

}
