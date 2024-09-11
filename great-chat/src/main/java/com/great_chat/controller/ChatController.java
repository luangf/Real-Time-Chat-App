package com.great_chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.great_chat.general.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.sendMessage") // if a message was send to this path, the method is called
	@SendTo("/topic/public") // Topic / Queue; send the message to here, the return value is broadcast to all subscribers
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender()); //add username in websocket session
		return chatMessage;
	}
	
}
