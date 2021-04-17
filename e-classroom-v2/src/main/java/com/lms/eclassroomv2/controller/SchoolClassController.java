package com.lms.eclassroomv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.services.SchoolClassService;

@RestController
public class SchoolClassController {

	@Autowired
	SchoolClassService schoolClassService;
}
