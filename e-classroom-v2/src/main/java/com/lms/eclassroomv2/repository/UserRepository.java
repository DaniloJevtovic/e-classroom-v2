package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);

	User findByUsername(String username);

	User findByEmail(String email);

}
