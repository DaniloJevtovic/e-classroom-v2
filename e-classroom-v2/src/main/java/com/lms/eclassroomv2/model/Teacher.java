package com.lms.eclassroomv2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User {

	private static final long serialVersionUID = 1L;
	
	public Teacher() {
		
	}

}
