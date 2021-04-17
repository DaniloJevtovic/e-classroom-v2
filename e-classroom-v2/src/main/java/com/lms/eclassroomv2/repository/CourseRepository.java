package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findById(Long courseId);
}
