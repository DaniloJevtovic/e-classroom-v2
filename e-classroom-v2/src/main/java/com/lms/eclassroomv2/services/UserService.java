package com.lms.eclassroomv2.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.User;
import com.lms.eclassroomv2.model.dto.UpdateUserDto;
import com.lms.eclassroomv2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

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

	public ResponseEntity<?> updateUser(String username, UpdateUserDto updateUserDto) {
		User user = getUserByUsername(username);

		if (user == null) {
			return new ResponseEntity<>("Ne postoji korisnik", HttpStatus.BAD_REQUEST);
		}

		if (updateUserDto.getNewPassword() != null) {
			user.setPassword(passwordEncoder.encode(updateUserDto.getNewPassword()));
		}

		try {
			user.setFirstName(updateUserDto.getFirstName());
			user.setLastName(updateUserDto.getLastName());
			user.setEmail(updateUserDto.getEmail());
			userRepository.save(user);

			Map<String, Object> map = new HashMap<>();
			map.put("user", userRepository.save(user));
			map.put("message", "podaci uspjesno azurirani");

			return new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Greska", HttpStatus.BAD_REQUEST);
		}
	}

}
