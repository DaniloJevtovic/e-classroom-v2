package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.eclassroomv2.model.Message;
import com.lms.eclassroomv2.model.dto.MessageDto;
import com.lms.eclassroomv2.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UserService userService;

	public Message getMessageById(Long messageId) {
		return messageRepository.findById(messageId).orElse(null);
	}

	public List<Message> getRecivedMessages(Long reciverId) {
		return messageRepository.findByReciverId(reciverId);
	}

	public List<Message> getSendedMessages(Long senderId) {
		return messageRepository.findBySenderId(senderId);
	}

	public ResponseEntity<?> sendMessage(MessageDto messageDto) {

		try {
			Message message = new Message();
			message.setSubject(messageDto.getSubject());
			message.setMessage(messageDto.getMessage());
			message.setSender(userService.getUserById(messageDto.getSenderId()));
			message.setReciver(userService.getUserById(messageDto.getReciverId()));
			message.setDate(new Timestamp(new Date().getTime()));

			messageRepository.save(message);

			return new ResponseEntity<>("Poruka uspjesno poslata", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce poslati poruku", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteMessageById(Long messageId) {
		messageRepository.deleteById(messageId);
	}
}
