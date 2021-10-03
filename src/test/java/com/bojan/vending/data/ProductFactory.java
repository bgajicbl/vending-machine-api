package com.bojan.vending.data;

import com.bojan.vending.model.Product;

public class ProductFactory {

    private ProductFactory() {

    }

    public static Product createWithDefaultValues(long id) {
        return Product.builder()
                .id(Long.valueOf(id))
                .productName("productName")
                .amountAvailable(1)
                .cost(Integer.valueOf(1))
                .sellerId(1L)
                .build();
    }

    public static Product createWithoutId() {
        return Product.builder()
                .productName("productName")
                .amountAvailable(1)
                .cost(Integer.valueOf(1))
                .sellerId(1L)
                .build();
    }
}
