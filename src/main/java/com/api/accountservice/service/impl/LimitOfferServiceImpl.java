package com.api.accountservice.service.impl;

import com.api.accountservice.Utils.AccountHelper;
import com.api.accountservice.constants.AccountStatus;
import com.api.accountservice.dto.LimitOfferDTO;
import com.api.accountservice.model.Account;
import com.api.accountservice.model.LimitOffer;
import com.api.accountservice.repository.AccountRepository;
import com.api.accountservice.repository.LimitOfferRepository;
import com.api.accountservice.service.LimitOfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LimitOfferServiceImpl implements LimitOfferService {

   private final AccountRepository accountRepository;
   private final LimitOfferRepository orderRepository;
   private final AccountHelper accountHelper;

   @Override
   public LimitOfferDTO createLimitOffer(LimitOfferDTO offerDTO) {
      Optional<Account> account = accountRepository.findAccountByAccountId(offerDTO.getAccountId());
      if (!account.isPresent()) {
         throw new RuntimeException("Account is not present");
      }
      switch (offerDTO.getLimitType()) {
         case ACCOUNT_LIMIT:
            return updateAccountLimit(account, offerDTO);
         case PER_TRANSACTION_LIMIT:
            return updateTransactionList(account, offerDTO);
         default:
            throw new RuntimeException("Invalid Limit Type");
      }
   }

   private LimitOfferDTO updateTransactionList(Optional<Account> account, LimitOfferDTO offerDTO) {
      Account accountData = account.get();
      if (accountData.getPerTransactionLimit() > offerDTO.getNewLimit()) {
         throw new RuntimeException("New Limit cannot be less then old limit");
      }
      accountData.setLastPerTransactionLimit(accountData.getPerTransactionLimit());
      accountData.setPerTransactionLimit(offerDTO.getNewLimit());
      accountData.setPerTransactionLimitUpdateTime(LocalDateTime.now());
      accountRepository.save(accountData);
      return accountHelper.LimitEntityToDTO(orderRepository.save(createOffer(offerDTO, accountData)));
   }

   public static LimitOffer createOffer(LimitOfferDTO offerDTO, Account accountData) {
      LimitOffer offer = LimitOffer
              .builder()
              .account(accountData)
              .newLimit(offerDTO.getNewLimit())
              .limitType(offerDTO.getLimitType())
              .offerActivationTime(offerDTO.getOfferActivationTime())
              .offerExpiryTime(offerDTO.getOfferExpiryTime())
              .build();
      return offer;
   }

   private LimitOfferDTO updateAccountLimit(Optional<Account> account, LimitOfferDTO offerDTO) {
      Account accountData = account.get();
      if (accountData.getAccountLimit() > offerDTO.getNewLimit()) {
         throw new RuntimeException("New Limit cannot be less then old limit");
      }
      accountData.setLastAccountLimit(accountData.getAccountLimit());
      accountData.setAccountLimit(offerDTO.getNewLimit());
      accountData.setAccountLimitUpdateTime(LocalDateTime.now());
      accountRepository.save(accountData);
      return accountHelper.LimitEntityToDTO(orderRepository.save(createOffer(offerDTO, accountData)));
   }

   @Override
   public List<LimitOfferDTO> getActiveLimitOffer(String accountId, String activeDate) {
      Optional<List<LimitOffer>> limitOffers = orderRepository.findAllByAccount_AccountId(accountId);
      if (limitOffers.isEmpty()) {
         throw new RestClientException("Invalid Request");
      }
      return limitOffers
              .get()
              .stream()
              .filter(limitOffer -> activeDate == null || isActiveOffer(limitOffer, activeDate))
              .map(accountHelper::LimitEntityToDTO)
              .collect(Collectors.toList());
   }

   @Override
   public LimitOfferDTO updateLimitOffer(String offerLimitId, String status) {
      Optional<LimitOffer> offer = orderRepository.findLimitOfferByLimitOfferId(offerLimitId);
      if (!offer.isPresent()) {
         throw new RestClientException("Offer is not present with this ID");
      }
      LimitOffer offerData = offer.get();
      offerData.setAccountStatus(AccountStatus.valueOf(status));
      return accountHelper.LimitEntityToDTO(orderRepository.save(offerData));
   }

   private boolean isActiveOffer(LimitOffer limitOffer, String activeDate) {
      LocalDate date = LocalDate.parse(activeDate);
      LocalDateTime time = date.atStartOfDay();
      return time.isBefore(limitOffer.getOfferExpiryTime());
   }
}
