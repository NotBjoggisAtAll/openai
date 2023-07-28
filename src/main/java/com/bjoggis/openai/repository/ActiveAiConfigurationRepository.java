package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.ActiveAiConfiguration;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActiveAiConfigurationRepository extends JpaRepository<ActiveAiConfiguration, Long> {

  @Query("select a from ActiveAiConfiguration a")
  Optional<ActiveAiConfiguration> findActive();
}
