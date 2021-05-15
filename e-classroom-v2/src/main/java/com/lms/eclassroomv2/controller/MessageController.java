package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.eclassroomv2.model.Message;
import com.lms.eclassroomv2.model.dto.MessageDto;
import com.lms.eclassroomv2.services.MessageService;

@RestController
@RequestMapping(value = "api/messages")
public class MessageController {

	@Autowired
	MessageService messageService;

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{messageId}")
	public Message getMessageById(@PathVariable Long messageId) {
		return messageService.getMessageById(messageId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{reciverId}")
	public List<Message> getRecivedMessages(@PathVariable Long reciverId) {
		return messageService.getRecivedMessages(reciverId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@GetMapping(value = "/{senderId}")
	public List<Message> getSendedMessages(@PathVariable Long senderId) {
		return messageService.getSendedMessages(senderId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@PostMapping
	public Message sendMessage(@RequestBody MessageDto messageDto) {
		return messageService.sendMessage(messageDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@DeleteMapping(value = "/{messageId}")
	public void deleteMessage(@PathVariable Long messageId) {
		messageService.deleteMessageById(messageId);
	}
}
