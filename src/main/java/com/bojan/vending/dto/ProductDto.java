package com.bojan.vending.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private Long id;

    @NotEmpty
    private String productName;
    private Integer amountAvailable;
    private Integer cost;
    private Long sellerId;
}
