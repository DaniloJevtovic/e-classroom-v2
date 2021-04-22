package com.lms.eclassroomv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	//svi ucenici iz odjeljenja
	List<Student> findByStudentClassId(Long studentClassId);
	
	//svi ucenici iz razreda
	List<Student> findByStudentClassSchoolClassId(Long schoolClassId);

}
