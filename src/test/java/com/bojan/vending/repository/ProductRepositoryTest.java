package com.bojan.vending.repository;

import com.bojan.vending.data.ProductFactory;
import com.bojan.vending.model.Product;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    public static final long PRODUCT_ID = 1L;
    public static final String PRODUCT_NAME = "productName";

    @Test
    void findById() {
        Product product = ProductFactory.createWithoutId();

        entityManager.persistAndFlush(product);
        Product found = productRepository.findById(product.getId()).get();
        assertEquals(found.getProductName(), product.getProductName());

    }

    @Test
    void findByProductName() {
    }
}