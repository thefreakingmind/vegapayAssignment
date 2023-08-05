package com.api.accountservice.model;

import com.api.accountservice.constants.LimitType;
import jakarta.persistence.*;
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
@Entity
@Table(name = "limit_offer")
public class LimitOffer {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private String id;

   @ManyToOne
   @JoinColumn(name = "account_id", nullable = false)
   private Account account;

   @Enumerated(EnumType.STRING)
   private LimitType limitType;

   @Column(name = "offer_activation_time")
   private LocalDateTime offerActivationTime;

   @Column(name = "offer_expiry_time")
   private LocalDateTime offerExpiryTime;
}
