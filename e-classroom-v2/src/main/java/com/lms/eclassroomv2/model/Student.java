package com.lms.eclassroomv2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("student")
public class Student extends User {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	StudentClass studentClass;
	
	@ManyToOne
	StudentParent stParent;
	
	public Student() {
		
	}

	public StudentClass getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClass studentClass) {
		this.studentClass = studentClass;
	}

	public StudentParent getStParent() {
		return stParent;
	}

	public void setStParent(StudentParent stParent) {
		this.stParent = stParent;
	}

}
