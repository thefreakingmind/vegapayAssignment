package com.api.accountservice.repository;

import com.api.accountservice.model.LimitOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
public interface LimitOfferRepository extends JpaRepository<LimitOffer, String> {

   public Optional<LimitOffer> findLimitOfferByLimitOfferId(String offerId);
}
