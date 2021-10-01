package com.bojan.vending.service;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.model.Product;
import com.bojan.vending.repository.ProductRepository;
import com.bojan.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@RequiredArgsConstructor
class ProductServiceImplTest {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
        Product product = Product.builder()
                .id(1)
                .productName("test_prod")
                .sellerId(1L)
                .amountAvailable(10)
                .cost(15)
                .build();
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(-99)).thenReturn(Optional.empty());
    }

    @Test
    void create() {
    }

    @Test
    void findProductById() {
        //ProductDto productDto = productService.findProductById(1);
        //assertEquals(productDto.getProductName(), "test_prod");

    }

    @Test
    void deleteProduct() {
    }
}