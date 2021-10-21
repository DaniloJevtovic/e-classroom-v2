package com.lms.eclassroomv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/{messageId}")
	public Message getMessageById(@PathVariable Long messageId) {
		return messageService.getMessageById(messageId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/seen/{messageId}")
	public Message getMessageByIdAndSee(@PathVariable Long messageId) {
		return messageService.getMessageByIdAndSetSeen(messageId);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/recived/{reciverId}")
	public List<Message> getRecivedMessages(@PathVariable Long reciverId) {
		return messageService.getRecivedMessages(reciverId);
	}

	// primljenje poruke sa paginacopm
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/recived/page/{reciverId}")
	public Page<Message> getRecivedMessagesPage(@PathVariable Long reciverId,
			@PageableDefault(size = 10) Pageable pageable) {
		return messageService.getRecivedMessagesPage(reciverId, pageable);
	}

	// primljenje neprocitanje poruke sa paginacopm
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('PARENT')")
	@GetMapping(value = "/unread/recived/page/{reciverId}")
	public Page<Message> getUnreadRecivedMessagesPage(@PathVariable Long reciverId,
			@PageableDefault(size = 10) Pageable pageable) {
		return messageService.getUnreadRecMessagesPage(reciverId, pageable);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')  or hasRole('PARENT')")
	@GetMapping(value = "/sent/{senderId}")
	public List<Message> getSendedMessages(@PathVariable Long senderId) {
		return messageService.getSendedMessages(senderId);
	}

	// poslate poruke sa paginacijom
	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')  or hasRole('PARENT')")
	@GetMapping(value = "/sent/page/{senderId}")
	public Page<Message> getSendedMessagesPage(@PathVariable Long senderId,
			@PageableDefault(size = 10) Pageable pageable) {
		return messageService.getSendedMessagesPage(senderId, pageable);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')  or hasRole('PARENT')")
	@PostMapping
	public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
		return messageService.sendMessage(messageDto);
	}

	@PreAuthorize("hasRole ('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
	@DeleteMapping(value = "/{messageId}")
	public void deleteMessage(@PathVariable Long messageId) {
		messageService.deleteMessageById(messageId);
	}
}
