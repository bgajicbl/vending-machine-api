package com.bojan.vending.service;

import com.bojan.vending.dto.BuyRequest;
import com.bojan.vending.dto.BuyResponse;
import com.bojan.vending.dto.CoinDto;
import com.bojan.vending.dto.mapper.CoinMapper;
import com.bojan.vending.exception.AmountNotSufficientException;
import com.bojan.vending.exception.EntityNotFoundException;
import com.bojan.vending.model.Product;
import com.bojan.vending.model.User;
import com.bojan.vending.repository.CoinRepository;
import com.bojan.vending.repository.ProductRepository;
import com.bojan.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CoinRepository coinRepository;

    @Override
    public int deposit(CoinDto coinDto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));
        int total = user.getDeposit() + CoinMapper.calculateTotal(coinDto);
        user.setDeposit(total);
        userRepository.save(user);
        return total;
    }

    @Override
    public synchronized BuyResponse buy(BuyRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:" + request.getProductId()));
        if (product.getAmountAvailable() < request.getAmount()) {
            throw new AmountNotSufficientException("Not sufficient amount of product with ID: " + request.getProductId());
        }

        int totalCost = request.getAmount() * product.getCost();
        if (user.getDeposit() < totalCost) {
            throw new AmountNotSufficientException("Not enough coins deposited!");
        }
        CoinDto change = calculateChange(user.getDeposit(), totalCost);

        BuyResponse response = BuyResponse.builder()
                .productName(product.getProductName())
                .deposited(user.getDeposit())
                .totalSpent(totalCost)
                .change(change)
                .build();

        product.setAmountAvailable(product.getAmountAvailable() - request.getAmount());
        productRepository.save(product);

        user.setDeposit(0);
        userRepository.save(user);

        return response;
    }

    private CoinDto calculateChange(int deposit, int price) {
        CoinDto result = new CoinDto();
        int change = deposit - price;

        int dollars = change / 100;
        result.setDollar(dollars);
        change = change % 100;

        int half = change / 50;
        result.setHalf(half);
        change = change % 50;

        int fifth = change / 20;
        result.setFifth(fifth);
        change = change % 20;

        int dime = change / 10;
        result.setDime(dime);
        change = change % 10;

        int nickel = change / 5;
        result.setNickel(nickel);

        return result;
    }
}
