package com.bojan.vending.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyRequest {

    @Positive
    private int productId;
    @Positive
    private int amount;

}
