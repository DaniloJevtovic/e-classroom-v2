package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.User;
import com.lms.eclassroomv2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
