package com.api.accountservice.service;

import com.api.accountservice.dto.AccountDTO;
import com.api.accountservice.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */


@Service
public interface AccountService {

   public Account createUserAccount(AccountDTO accountDTO);

   public Optional<AccountDTO> getAllAccounts(String accountId);

}
