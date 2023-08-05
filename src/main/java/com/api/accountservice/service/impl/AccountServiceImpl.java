package com.api.accountservice.service.impl;

import com.api.accountservice.Utils.AccountHelper;
import com.api.accountservice.dto.AccountDTO;
import com.api.accountservice.model.Account;
import com.api.accountservice.repository.AccountRepository;
import com.api.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

   private final AccountRepository accountRepository;
   private final AccountHelper accountHelper;

   @Override
   public Account createUserAccount(AccountDTO accountDTO) {
      Account account = Account
              .builder()
              .customerId("CUSTOMER"+UUID.randomUUID().toString())
              .accountLimit(accountDTO.getAccountLimit())
              .perTransactionLimit(accountDTO.getPerTransactionLimit())
              .lastAccountLimit(accountDTO.getLastAccountLimit())
              .lastPerTransactionLimit(accountDTO.getLastPerTransactionLimit())
              .accountLimitUpdateTime(LocalDateTime.now())
              .perTransactionLimitUpdateTime(LocalDateTime.now())
              .build();
      Account saveData = accountRepository.save(account);
      return saveData;

   }

   @Override
   public Optional<List<AccountDTO>> getAllAccounts(String accountId) {
      Optional<List<Account>> getAccountsForAccountId = accountRepository.findAllByAccountId(accountId);
      if(getAccountsForAccountId.isEmpty()){
         return Optional.empty();
      }
      return Optional.ofNullable(getAccountsForAccountId
              .get()
              .stream()
              .map(accountHelper::AccountEntityToDTO)
              .collect(Collectors.toList()));
   }
}
