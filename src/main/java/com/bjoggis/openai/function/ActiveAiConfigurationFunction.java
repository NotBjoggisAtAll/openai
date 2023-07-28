package com.bjoggis.openai.function;

import com.bjoggis.openai.entity.AiConfiguration;
import com.bjoggis.openai.exception.ActiveAiConfigurationException;
import com.bjoggis.openai.repository.ActiveAiConfigurationRepository;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class ActiveAiConfigurationFunction implements Supplier<AiConfiguration> {

  private final ActiveAiConfigurationRepository repository;

  public ActiveAiConfigurationFunction(
      ActiveAiConfigurationRepository repository) {
    this.repository = repository;
  }

  /**
   * Get the active AI configuration
   *
   * @return the active AI configuration
   * @throws ActiveAiConfigurationException if no active AI configuration is found
   */
  @Override
  public AiConfiguration get() {
    //@formatter:off
    return repository
        .findActive()
          .orElseThrow(ActiveAiConfigurationException::new)
        .getAiConfiguration();
    //@formatter:on
  }
}
