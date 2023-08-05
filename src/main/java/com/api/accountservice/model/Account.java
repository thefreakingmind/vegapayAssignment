package com.api.accountservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "account")
@Entity
public class Account {

   @Id
   @GeneratedValue(generator="system-uuid")
   @GenericGenerator(name="system-uuid", strategy = "uuid")
   private String accountId;
   private String customerId;
   private Double accountLimit;
   private Double perTransactionLimit;
   private Double lastAccountLimit;
   private Double lastPerTransactionLimit;
   private LocalDateTime accountLimitUpdateTime;
   private LocalDateTime perTransactionLimitUpdateTime;
   @OneToMany(mappedBy = "account")
   private List<LimitOffer> limitOffers;
}
