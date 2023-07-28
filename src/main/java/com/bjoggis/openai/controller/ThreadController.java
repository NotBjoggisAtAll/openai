package com.bjoggis.openai.controller;

import com.bjoggis.openai.entity.Account;
import com.bjoggis.openai.entity.Message;
import com.bjoggis.openai.entity.ThreadChannel;
import com.bjoggis.openai.repository.AccountRepository;
import com.bjoggis.openai.repository.ThreadChannelRepository;
import com.bjoggis.openai.resource.MessageResource;
import com.bjoggis.openai.type.Sender;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/thread")
public class ThreadController {

  private final ThreadChannelRepository threadChannelRepository;
  private final AccountRepository accountRepository;

  public ThreadController(ThreadChannelRepository threadChannelRepository,
      AccountRepository accountRepository) {
    this.threadChannelRepository = threadChannelRepository;
    this.accountRepository = accountRepository;
  }

  @PostMapping
  @Transactional
  public String createThread(Principal principal) {

    Account account = accountRepository.findByUsername(principal.getName());

    ThreadChannel threadChannel = new ThreadChannel();
    threadChannel.setThreadId(UUID.randomUUID().toString());
    threadChannel.setUser(account);
    ThreadChannel entity = threadChannelRepository.save(threadChannel);
    return entity.getThreadId();
  }

  @GetMapping("/ids")
  @Transactional(readOnly = true)
  public List<String> getAllThreadsIds(Principal principal) {
    Account account = accountRepository.findByUsername(principal.getName());

    List<ThreadChannel> channels = threadChannelRepository.findByUser(account);

    return channels.stream()
        .map(ThreadChannel::getThreadId)
        .toList();
  }

  @GetMapping("/{threadId}/messages")
  @Transactional(readOnly = true)
  public List<MessageResource> getAllMessages(Principal principal, @PathVariable String threadId) {
    Account account = accountRepository.findByUsername(principal.getName());

    Optional<ThreadChannel> channel = threadChannelRepository.findByThreadIdAndUserOrderById(
        threadId,
        account);

    if (channel.isEmpty()) {
      return List.of();
    }

    ThreadChannel threadChannel = channel.get();

    List<Message> messages = threadChannel.getMessages();

    return messages.stream()
        .filter(message -> message.getSender() == Sender.ASSISTANT
            || message.getSender() == Sender.USER)
        .map(message -> new MessageResource(message.getMessageId(), message.getMessageAsString(),
            threadChannel.getThreadId(), message.getSender() == Sender.ASSISTANT))
        .toList();
  }

}
