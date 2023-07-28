package com.bjoggis.openai.resource;

public record MessageResource(String messageId, String message, String threadId, boolean isBot) {

}
