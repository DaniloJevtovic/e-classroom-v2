package com.lms.eclassroomv2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lms.eclassroomv2.exception.ResourceConflictException;
import com.lms.eclassroomv2.model.User;
import com.lms.eclassroomv2.model.dto.RegisterDto;
import com.lms.eclassroomv2.model.dto.LoginDto;
import com.lms.eclassroomv2.model.dto.TokenDto;
import com.lms.eclassroomv2.security.TokenUtils;
import com.lms.eclassroomv2.services.CustomUserDetailsService;
import com.lms.eclassroomv2.services.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<TokenDto> createAuthenticationToken(
			@RequestBody LoginDto loginDto, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();

		String jwt = tokenUtils.generateToken(user);
		int expiresIn = tokenUtils.getExpiredIn();

		return ResponseEntity.ok(new TokenDto(jwt, expiresIn));
	}

//	@PostMapping("/signup")
//	public ResponseEntity<User> addUser(@RequestBody RegisterDto userRequest, UriComponentsBuilder ucBuilder) {
//
//		User existUser = this.userService.findByUsername(userRequest.getUsername());
//		if (existUser != null) {
//			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
//		}
//
//		User user = this.userService.save(userRequest);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
//		return new ResponseEntity<>(user, HttpStatus.CREATED);
//	}

	@PostMapping(value = "/refresh")
	public ResponseEntity<TokenDto> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new TokenDto(refreshedToken, expiresIn));
		} else {
			TokenDto userTokenState = new TokenDto();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('STUDNET') or hasROLE('TEACHER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}