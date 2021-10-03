package com.bojan.vending.data;

import com.bojan.vending.dto.ProductDto;

public class ProductDTOFactory {

    private ProductDTOFactory() {
        // Private constructor to hide the implicit public one.
    }

    public static ProductDto createWithDefaultValues(long id) {
        return ProductDto.builder()
                .id(id)
                .productName("productName")
                .amountAvailable(1)
                .cost(Integer.valueOf(1))
                .sellerId(1L)
                .build();
    }
}
