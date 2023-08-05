package com.api.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AccountDTO {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String customerId;

   private Double accountLimit;

   private Double perTransactionLimit;

   private Double lastAccountLimit;

   private Double lastPerTransactionLimit;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime accountLimitUpdateTime;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime perTransactionLimitUpdateTime;
}
