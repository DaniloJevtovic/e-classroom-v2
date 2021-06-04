package com.lms.eclassroomv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.repository.AssignmentRepository;

@Service
public class AssignmentService {
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
}
