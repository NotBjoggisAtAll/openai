package com.bjoggis.openai.type;

public enum Sender {
  USER("User"),
  ASSISTANT("Assistant"),
  SYSTEM("System");

  private final String comment;

  Sender(String comment) {
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }
}
