package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Optional<Authority> findById(Long id);

	Authority findByName(String name);
}
