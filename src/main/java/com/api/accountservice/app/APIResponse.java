package com.api.accountservice.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Salman aka theFreakingMind
 * @date 05/08/23
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponse <T>{

   private T data;
   private String message;
   private boolean success;
}
