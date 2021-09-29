package com.bojan.vending.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinDto {

    private int nickel;
    private int dime;
    private int fifth;
    private int half;
    private int dollar;
    private long total;

    private Long userId;
}
