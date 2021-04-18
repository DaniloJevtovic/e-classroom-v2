package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Optional<Teacher> findById(Long teacherId);
}
