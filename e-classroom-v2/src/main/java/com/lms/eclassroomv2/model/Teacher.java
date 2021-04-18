package com.lms.eclassroomv2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {

	private static final long serialVersionUID = 1L;

	// atribut koji ce mi pomoci da razlikujem profesore - ako imam 2 ili vise
	// profesora sa istim imenom i prezimenom da znam kome da dodjeljum predmet
	private String subjects;

	public Teacher() {

	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

}
