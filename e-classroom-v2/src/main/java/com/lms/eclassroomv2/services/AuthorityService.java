package com.lms.eclassroomv2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Authority;
import com.lms.eclassroomv2.repository.AuthorityRepository;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public List<Authority> findById(Long id) {
		Authority authority = this.authorityRepository.getOne(id);
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		return authorities;
	}

	public List<Authority> findByname(String name) {
		Authority authority = this.authorityRepository.findByName(name);
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		return authorities;
	}

}
