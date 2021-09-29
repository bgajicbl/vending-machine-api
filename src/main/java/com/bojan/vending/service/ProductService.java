package com.bojan.vending.service;

import com.bojan.vending.dto.ProductDto;

public interface ProductService {

    ProductDto create(ProductDto productDto, String username);

    ProductDto findProductById(int id);

    void deleteProduct(long id);
}
