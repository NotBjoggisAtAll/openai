package com.bjoggis.openai.controller;

import com.bjoggis.openai.command.ChatCommand;
import com.bjoggis.openai.entity.ThreadChannel;
import com.bjoggis.openai.repository.ThreadChannelRepository;
import com.bjoggis.openai.service.ChatService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {

  private final Logger logger = LoggerFactory.getLogger(ChatController.class);
  private final ChatService chatService;
  private final ThreadChannelRepository threadChannelRepository;

  public ChatController(ChatService chatService, ThreadChannelRepository threadChannelRepository) {
    this.chatService = chatService;
    this.threadChannelRepository = threadChannelRepository;
  }

  @GetMapping
  @Transactional
  public String chat(@RequestBody @Valid ChatCommand command, Principal principal) {
    logger.info("User {} is chatting with message: {}", principal.getName(),command.message());
    return chatService.chat(UUID.randomUUID().toString(), command.message(), command.threadId(), principal.getName());
  }
}
