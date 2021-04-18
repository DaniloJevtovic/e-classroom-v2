package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	AuthorityRepository authorityRepository;
	
	public List<Authority> getAllAuthorities() {
		return authorityRepository.findAll();
	}
	
	public Authority getAuthorityByName(String name) {
		return authorityRepository.findByName(name);
	}
	
}
