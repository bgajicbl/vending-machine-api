package com.bojan.vending.dto.mapper;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .amountAvailable(product.getAmountAvailable())
                .cost(product.getCost())
                .sellerId(product.getSellerId())
                .build();
    }

    public static Product toProduct(ProductDto productDto){
        return Product.builder()
                .id(productDto.getId())
                .productName(productDto.getProductName())
                .amountAvailable(productDto.getAmountAvailable())
                .cost(productDto.getCost())
                .sellerId(productDto.getSellerId())
                .build();
    }
}
