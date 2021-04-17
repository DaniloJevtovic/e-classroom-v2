package com.lms.eclassroomv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.repository.SchoolClassRepository;

@Service
public class SchoolClassService {

	@Autowired
	SchoolClassRepository schoolClassRepository;
}
