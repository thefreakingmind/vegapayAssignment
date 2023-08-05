package com.api.accountservice.dto;

import com.api.accountservice.constants.AccountStatus;
import com.api.accountservice.constants.LimitType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitOfferDTO {

   public String accountId;

   @Enumerated(EnumType.STRING)
   private LimitType limitType;

   private Double newLimit;

   private LocalDateTime offerActivationTime;

   private LocalDateTime offerExpiryTime;

   @Enumerated(EnumType.STRING)
   private AccountStatus accountStatus;

}
