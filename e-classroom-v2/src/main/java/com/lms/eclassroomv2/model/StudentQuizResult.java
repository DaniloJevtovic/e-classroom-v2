package com.lms.eclassroomv2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_quiz_results")
public class StudentQuizResult {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int points;

	//koliko je ucenik vremenski rjesavao kviz
	@Column(name = "solve_duration")
	private double solveDuration;
	
	@ManyToOne
	private Student student;

	@ManyToOne
	private Quiz quiz;

	public StudentQuizResult() {

	}

	public StudentQuizResult(int points, Student student, Quiz quiz) {
		super();
		this.points = points;
		this.student = student;
		this.quiz = quiz;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public double getSolveDuration() {
		return solveDuration;
	}

	public void setSolveDuration(double solveDuration) {
		this.solveDuration = solveDuration;
	}
}
