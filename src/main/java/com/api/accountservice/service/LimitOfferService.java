package com.api.accountservice.service;

import com.api.accountservice.dto.LimitOfferDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Service
public interface LimitOfferService {


   public LimitOfferDTO createLimitOffer(LimitOfferDTO offerDTO);

   public List<LimitOfferDTO> getActiveLimitOffer(String accountId, String activeDate);

   public LimitOfferDTO updateLimitOffer(String offerId, String status);
}
