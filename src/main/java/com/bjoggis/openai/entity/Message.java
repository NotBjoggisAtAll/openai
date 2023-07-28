package com.bjoggis.openai.entity;

import com.bjoggis.openai.type.Sender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "message", schema = "spillhuset", catalog = "spillhuset")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String messageId;

  private Sender sender;

  @Lob
  @Column(columnDefinition = "blob")
  private byte[] message;

  private LocalDateTime created;

  @ManyToOne(optional = false)
  @JoinColumn(nullable = false, updatable = false)
  private ThreadChannel threadChannel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public byte[] getMessage() {
    return message;
  }

  public String getMessageAsString() {
    return new String(message);
  }

  public void setMessage(byte[] message) {
    this.message = message;
  }

  public void setMessage(String message) {
    this.message = message.getBytes();
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public ThreadChannel getThreadChannel() {
    return threadChannel;
  }

  public void setThreadChannel(ThreadChannel threadChannel) {
    this.threadChannel = threadChannel;
  }
}
