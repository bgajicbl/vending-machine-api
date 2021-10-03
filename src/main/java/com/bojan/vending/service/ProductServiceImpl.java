package com.bojan.vending.service;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.dto.mapper.ProductMapper;
import com.bojan.vending.exception.EntityExistsException;
import com.bojan.vending.exception.EntityNotFoundException;
import com.bojan.vending.model.Product;
import com.bojan.vending.model.User;
import com.bojan.vending.repository.ProductRepository;
import com.bojan.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public ProductDto create(final ProductDto productDto, String username) {
        Optional<Product> productOpt = productRepository.findByProductName(productDto.getProductName());
        if (productOpt.isPresent()) {
            throw new EntityExistsException(Product.class, "product", productDto.getProductName());
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));

        Product product = Product.builder()
                .productName(productDto.getProductName())
                .amountAvailable(productDto.getAmountAvailable())
                .cost(productDto.getCost())
                .sellerId(user.getId())
                .build();
        log.info("Created product with name: " + productDto.getProductName());
        return ProductMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto findProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:" + id));
        return ProductMapper.toProductDto(product);
    }

    @Override
    public void deleteProduct(int id, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));

        Product product = productRepository.findByIdAndSellerId(id, user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product for user: " + username + " not found with ID:" + id));

        product.setDeleted(true);
        productRepository.save(product);
        log.info("Deleted product with ID: " + id);

    }

}
