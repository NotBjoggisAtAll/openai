package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.Account;
import com.bjoggis.openai.entity.ThreadChannel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThreadChannelRepository extends JpaRepository<ThreadChannel, Long> {

  @Query("select t from ThreadChannel t where t.threadId = ?1")
  ThreadChannel findByThreadId(String threadId);

  @Query("select t from ThreadChannel t where t.threadId = ?1 and t.user = ?2 order by t.id asc")
  Optional<ThreadChannel> findByThreadIdAndUserOrderById(String threadId, Account account);

  @Query("select t from ThreadChannel t where t.user = ?1")
  List<ThreadChannel> findByUser(Account account);
}
