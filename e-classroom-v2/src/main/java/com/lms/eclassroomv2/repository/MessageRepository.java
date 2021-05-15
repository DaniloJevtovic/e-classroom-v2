package com.lms.eclassroomv2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	Optional<Message> findById(Long messageId);
}
