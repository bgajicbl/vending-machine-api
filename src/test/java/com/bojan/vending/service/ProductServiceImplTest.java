package com.bojan.vending.service;

import com.bojan.vending.data.ProductDTOFactory;
import com.bojan.vending.data.ProductFactory;
import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.dto.mapper.ProductMapper;
import com.bojan.vending.exception.EntityNotFoundException;
import com.bojan.vending.model.Product;
import com.bojan.vending.repository.ProductRepository;
import com.bojan.vending.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Rollback
class ProductServiceImplTest {

    private static long PRODUCT_ID = 1L;

    private static String ENTITY_NOT_FOUND_MESSAGE = "Product not found with ID:" + PRODUCT_ID;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    private Product product;

    private ProductDto productDto;

    //Class under test
    private ProductService cut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productDto = ProductDTOFactory.createWithDefaultValues(PRODUCT_ID);
        product = ProductFactory.createWithDefaultValues(PRODUCT_ID);
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
        cut = new ProductServiceImpl(productRepository, userRepository);
    }

    @Test
    void givenProductId_when_findProductById_thenReturnProductDto() {
        try (MockedStatic<ProductMapper> productMapperMock = Mockito.mockStatic(ProductMapper.class)) {
            productMapperMock.when(() -> ProductMapper.toProductDto(product))
                    .thenReturn(productDto);
        }
        ProductDto result = cut.findProductById(PRODUCT_ID);

        assertThat(result).isNotNull().isEqualTo(productDto);

    }

    @Test
    void givenUnknownProductId_when_findProductById_thenThrowEntityNotFoundException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cut.findProductById(Integer.MAX_VALUE);
        }, ENTITY_NOT_FOUND_MESSAGE);
    }

}