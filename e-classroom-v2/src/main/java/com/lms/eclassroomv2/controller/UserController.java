package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.User;
import com.lms.eclassroomv2.model.dto.UpdateUserDto;
import com.lms.eclassroomv2.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	UserService userService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping()
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping("/username/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@PutMapping("/{username}")
	public ResponseEntity<?> update(@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
		return userService.updateUser(username, updateUserDto);
	}

}
