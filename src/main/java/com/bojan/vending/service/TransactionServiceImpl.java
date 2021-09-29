package com.bojan.vending.service;

import com.bojan.vending.dto.CoinDto;
import com.bojan.vending.dto.mapper.CoinMapper;
import com.bojan.vending.exception.EntityNotFoundException;
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
        int total = 0;
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));
        total = user.getDeposit() + CoinMapper.calculateTotal(coinDto);
        user.setDeposit(total);
        userRepository.save(user);
        return total;
    }
}
