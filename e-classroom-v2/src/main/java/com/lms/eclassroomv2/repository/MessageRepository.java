package com.lms.eclassroomv2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.eclassroomv2.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	Optional<Message> findById(Long messageId);

	List<Message> findBySenderId(Long senderId);

	List<Message> findBySenderIdOrderByDateDesc(Long senderId);
	
	// lista poslatih poruka sa paginacijom
	Page<Message> findBySenderIdOrderByDateDesc(Long senderId, Pageable pageable);

	List<Message> findByReciverId(Long reciverId);

	List<Message> findByReciverIdOrderByDateDesc(Long reciverId);

	// lista svih poruka za primaoca sa paginacijom
	Page<Message> findByReciverIdOrderByDateDesc(Long reciverId, Pageable pageable);

}
