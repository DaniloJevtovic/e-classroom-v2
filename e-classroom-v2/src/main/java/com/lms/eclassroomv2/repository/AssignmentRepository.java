package com.lms.eclassroomv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
