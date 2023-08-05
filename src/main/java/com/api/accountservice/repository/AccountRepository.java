package com.api.accountservice.repository;

import com.api.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
public interface AccountRepository extends JpaRepository<Account, String> {

   public Optional<Account> findAccountByAccountId(String accountId);

   public Optional<List<Account>> findAllByAccountId(String accountId);
}
