package com.lms.eclassroomv2.model;

import java.sql.Timestamp;

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
@Table(name = "quizzes")
public class Quiz {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "instructions")
	private String instructions;

	@Column(name = "duration")
	private int duration;
	
	@Column(name = "total_points")
	private int totalPoints;

	/**
	 * dateFrom i dateTo je period u kojem ce moci da se radi kviz - npr. u pon od
	 * 10h ujutro do 20h kad se pokrene kviz maksimalno vrjeme rjesavanja kviza je
	 * definisano atributom duration
	 */

	@Column(name = "date_from")
	private Timestamp dateFrom;

	@Column(name = "date_to")
	private Timestamp dateTo;

	@Column(name = "quiz_status")
	@Enumerated(EnumType.ORDINAL)
	QuizStatus quizStatus;

	@ManyToOne
	Course course;

	public Quiz() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public QuizStatus getQuizStatus() {
		return quizStatus;
	}

	public void setQuizStatus(QuizStatus quizStatus) {
		this.quizStatus = quizStatus;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

}
