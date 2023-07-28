package com.bjoggis.openai.repository;

import com.bjoggis.openai.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Account findByUsername(String username);
}
