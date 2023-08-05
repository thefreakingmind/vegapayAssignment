package com.api.accountservice.controller;

import com.api.accountservice.app.APIResponse;
import com.api.accountservice.constants.Menu;
import com.api.accountservice.dto.LimitOfferDTO;
import com.api.accountservice.service.LimitOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@RequestMapping(Menu.OFFER_MENU)
@RestController
@RequiredArgsConstructor
public class LimitOfferController {

   private final LimitOfferService orderService;

   @PostMapping("/create")
   public APIResponse<LimitOfferDTO> createLimitOffer(@RequestBody LimitOfferDTO limitOfferDTO){
      return APIResponse.<LimitOfferDTO>builder()
              .data(orderService.createLimitOffer(limitOfferDTO))
              .message("Fetched")
              .success(true)
              .build();
   }

   @PostMapping("/update")
   public APIResponse<LimitOfferDTO> updateLimitOffer(@RequestParam("limitOfferId") String limitOfferId,
                                                      @RequestParam("status") String status){
      return APIResponse.<LimitOfferDTO>builder()
              .data(orderService.updateLimitOffer(limitOfferId, status))
              .message("Fetched")
              .success(true)
              .build();
   }
}
