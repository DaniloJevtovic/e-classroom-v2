package com.lms.eclassroomv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Authority findByName(String name);
}
