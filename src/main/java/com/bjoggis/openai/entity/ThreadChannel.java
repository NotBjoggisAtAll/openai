package com.bjoggis.openai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "thread_channel", schema = "spillhuset", catalog = "spillhuset")
public class ThreadChannel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String threadId;

  private String originalMessageId;

  @OneToMany(mappedBy = "threadChannel")
  private List<Message> messages;

  @ManyToOne
  private Account user;


  public String getOriginalMessageId() {
    return originalMessageId;
  }

  public void setOriginalMessageId(String originalMessageId) {
    this.originalMessageId = originalMessageId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public Account getUser() {
    return user;
  }

  public void setUser(Account user) {
    this.user = user;
  }
}
