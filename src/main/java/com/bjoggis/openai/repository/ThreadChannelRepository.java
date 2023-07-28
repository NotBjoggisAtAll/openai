package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.ThreadChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThreadChannelRepository extends JpaRepository<ThreadChannel, Long> {

  @Query("select t from ThreadChannel t where t.threadId = ?1")
  ThreadChannel findByThreadId(String threadId);

}
