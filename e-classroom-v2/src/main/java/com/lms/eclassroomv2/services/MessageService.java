package com.lms.eclassroomv2.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	// dobavljanje poruke i oznacavanje poruke kao procitane
	public Message getMessageByIdAndSetSeen(Long messageId) {
		Message message = messageRepository.findById(messageId).orElse(null);
		message.setSeen(true);

		return messageRepository.save(message);
	}

	public List<Message> getRecivedMessages(Long reciverId) {
		// return messageRepository.findByReciverId(reciverId);
		return messageRepository.findByReciverIdOrderByDateDesc(reciverId);
	}

	// dobavljanje primljenih poruka sa paginacijom i sortiranje po datumu
	public Page<Message> getRecivedMessagesPage(Long reciverId, Pageable pageable) {
		return messageRepository.findByReciverIdOrderByDateDesc(reciverId, pageable);
	}

	public List<Message> getSendedMessages(Long senderId) {
		// return messageRepository.findBySenderId(senderId);
		return messageRepository.findBySenderIdOrderByDateDesc(senderId);
	}

	// poslate poruke sa paginacijom, datum opadajuci
	public Page<Message> getSendedMessagesPage(Long senderId, Pageable pageable) {
		return messageRepository.findBySenderIdOrderByDateDesc(senderId, pageable);
	}

	public ResponseEntity<?> sendMessage(MessageDto messageDto) {

		try {
			Message message = new Message();
			message.setSubject(messageDto.getSubject());
			message.setMessage(messageDto.getMessage());
			message.setSender(userService.getUserById(messageDto.getSenderId()));
			message.setReciver(userService.getUserById(messageDto.getReciverId()));
			message.setDate(new Timestamp(new Date().getTime()));
			message.setSeen(false);

			// messageRepository.save(message);

			// return new ResponseEntity<>("Poruka uspjesno poslata", HttpStatus.OK);

			Map<String, Object> res = new HashMap<String, Object>();
			res.put("body", messageRepository.save(message));
			res.put("message", "Poruka uspjesno poslata");

			return ResponseEntity.ok(res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Nije moguce poslati poruku", HttpStatus.BAD_REQUEST);
		}

	}

	public void deleteMessageById(Long messageId) {
		messageRepository.deleteById(messageId);
	}
}
