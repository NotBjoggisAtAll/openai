package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.Message;
import com.bjoggis.openai.entity.ThreadChannel;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findByThreadChannel_ThreadId(String threadId);

  Set<Message> findByThreadChannel_ThreadIdOrderByCreatedAsc(String threadId);


  long deleteByThreadChannel(ThreadChannel threadChannel);
}
