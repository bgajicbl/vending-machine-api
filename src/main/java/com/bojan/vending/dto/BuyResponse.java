package com.bojan.vending.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyResponse {

    private int deposited;
    private int totalSpent;
    private String productName;
    private CoinDto change;

}
