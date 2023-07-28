package com.bjoggis.openai.controller;

import com.bjoggis.openai.entity.ThreadChannel;
import com.bjoggis.openai.repository.ThreadChannelRepository;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/thread")
public class ThreadController {

  private final ThreadChannelRepository threadChannelRepository;

  public ThreadController(ThreadChannelRepository threadChannelRepository) {
    this.threadChannelRepository = threadChannelRepository;
  }

  @PostMapping
  public String createThread() {
    ThreadChannel threadChannel = new ThreadChannel();
    threadChannel.setThreadId(UUID.randomUUID().toString());
    ThreadChannel entity = threadChannelRepository.save(threadChannel);
    return entity.getThreadId();
  }

}
