package com.bjoggis.openai.exception;

public class ActiveAiConfigurationException extends RuntimeException {

  public ActiveAiConfigurationException() {
    super("No active AI configuration found");
  }
}
