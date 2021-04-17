package com.lms.eclassroomv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.StudentClass;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

}
