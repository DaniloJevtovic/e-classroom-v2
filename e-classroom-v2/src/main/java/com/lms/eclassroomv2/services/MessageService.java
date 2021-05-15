package com.lms.eclassroomv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Message sendMessage(MessageDto messageDto) {
		Message message = new Message();
		message.setSubject(messageDto.getSubject());
		message.setMessage(messageDto.getMessage());
		message.setSender(userService.getUserById(messageDto.getSenderId()));
		message.setReciver(userService.getUserById(messageDto.getReciverId()));

		return messageRepository.save(message);
	}

	public void deleteMessageById(Long messageId) {
		messageRepository.deleteById(messageId);
	}
}
