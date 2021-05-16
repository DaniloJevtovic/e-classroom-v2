package com.lms.eclassroomv2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("parent")
public class StudentParent extends User {

	private static final long serialVersionUID = 1L;

	public StudentParent() {

	}

}
