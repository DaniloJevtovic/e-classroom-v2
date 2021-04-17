package com.lms.eclassroomv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
