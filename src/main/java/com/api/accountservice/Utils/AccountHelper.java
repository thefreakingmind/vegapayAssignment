package com.api.accountservice.Utils;

import com.api.accountservice.dto.AccountDTO;
import com.api.accountservice.dto.LimitOfferDTO;
import com.api.accountservice.model.Account;
import com.api.accountservice.model.LimitOffer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Component
@Slf4j
public class AccountHelper {

   /**
    * Improvement Move to MapStruct
    * @param account
    * @return
    */
   public AccountDTO AccountEntityToDTO(Account account){
      return AccountDTO
              .builder()
              .customerId(account.getCustomerId())
              .accountLimit(account.getAccountLimit())
              .perTransactionLimit(account.getPerTransactionLimit())
              .lastAccountLimit(account.getLastAccountLimit())
              .lastPerTransactionLimit(account.getLastPerTransactionLimit())
              .accountLimitUpdateTime(account.getAccountLimitUpdateTime())
              .perTransactionLimitUpdateTime(account.getPerTransactionLimitUpdateTime())
              .build();
   }

   public LimitOfferDTO LimitEntityToDTO(LimitOffer offer){
      return LimitOfferDTO
              .builder()
              .newLimit(offer.getNewLimit())
              .accountId(offer.getAccount().getAccountId())
              .limitType(offer.getLimitType())
              .offerActivationTime(offer.getOfferActivationTime())
              .offerExpiryTime(offer.getOfferExpiryTime())
              .accountStatus(offer.getAccountStatus())
              .build();
   }
}
