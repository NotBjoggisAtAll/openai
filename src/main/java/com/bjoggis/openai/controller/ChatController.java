package com.bjoggis.openai.controller;

import com.bjoggis.openai.command.ChatCommand;
import com.bjoggis.openai.entity.Account;
import com.bjoggis.openai.repository.AccountRepository;
import com.bjoggis.openai.repository.ThreadChannelRepository;
import com.bjoggis.openai.resource.MessageResource;
import com.bjoggis.openai.service.ChatService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {

  private final Logger logger = LoggerFactory.getLogger(ChatController.class);
  private final ChatService chatService;
  private final ThreadChannelRepository threadChannelRepository;
  private final AccountRepository accountRepository;

  public ChatController(ChatService chatService, ThreadChannelRepository threadChannelRepository,
      AccountRepository accountRepository) {
    this.chatService = chatService;
    this.threadChannelRepository = threadChannelRepository;
    this.accountRepository = accountRepository;
  }

  @PostMapping
  @Transactional
  public MessageResource chat(@RequestBody @Valid ChatCommand command, Principal principal) {

    Account account = accountRepository.findByUsername(principal.getName());
    threadChannelRepository.findByThreadIdAndUserOrderById(command.threadId(), account)
        .orElseThrow(() -> new RuntimeException("Thread not found"));

    String messageId = UUID.randomUUID().toString();

    logger.info("User {} is chatting with message: {}", principal.getName(), command.message());
    String message = chatService.chat(messageId, command.message(),
        command.threadId(), principal.getName());

    return new MessageResource(messageId, message, command.threadId(), true);
  }
}
