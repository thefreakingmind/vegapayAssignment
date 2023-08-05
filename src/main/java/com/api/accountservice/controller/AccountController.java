package com.api.accountservice.controller;

import com.api.accountservice.app.APIResponse;
import com.api.accountservice.constants.Menu;
import com.api.accountservice.dto.AccountDTO;
import com.api.accountservice.model.Account;
import com.api.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@RestController
@RequestMapping(Menu.ACCOUNT_MENU)
@RequiredArgsConstructor
@Slf4j
public class AccountController {

   private final AccountService accountService;

   @PostMapping("/create")
   public APIResponse<Account> createAccount(@RequestBody AccountDTO accountDTO){
      log.info("creating new account");
      return APIResponse.<Account>builder()
              .message("Fetched")
              .success(true)
              .data(accountService.createUserAccount(accountDTO))
              .build();
   }

   @GetMapping("/get")
   public APIResponse<List<AccountDTO>> getAllAccount(@RequestParam("accountId") String accountId){
      return APIResponse.<List<AccountDTO>>builder()
              .message("Fetched")
              .data(accountService.getAllAccounts(accountId).get())
              .success(true)
              .build();
   }
}
