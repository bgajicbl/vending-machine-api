package com.bojan.vending.repository;

import com.bojan.vending.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findById(int id);
    Optional<Product> findByProductName(String productName);
}