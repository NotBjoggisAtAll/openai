package com.bjoggis.openai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OpenaiApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenaiApplication.class, args);
  }

}
