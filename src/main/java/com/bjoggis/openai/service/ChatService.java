package com.bjoggis.openai.service;

public interface ChatService {


  String chat(String messageId, String message, String threadId, String userId);
}
