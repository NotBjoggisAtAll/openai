package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.AiConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiConfigurationRepository extends JpaRepository<AiConfiguration, Long> {

}
