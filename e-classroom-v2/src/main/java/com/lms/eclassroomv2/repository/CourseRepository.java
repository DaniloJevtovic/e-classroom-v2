package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findById(Long courseId);
	
	// lista svih predmeta za profesora (i obrisani)
	List<Course> findByTeacherId(Long teacherId);
	
	// lista predmeta za profesora koji nisu obrisani
	List<Course> findByTeacherIdAndDeletedFalse(Long teacherId);
	
	// lista svih predmeta za razred (i obrisani)
	List<Course> findBySchoolClassId(Long schoolClassId);
	
	//lista predmeta za razred koji nisu obrisani
	List<Course> findBySchoolClassIdAndDeletedFalse(Long schoolClassId);
}
