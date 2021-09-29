package com.bojan.vending.service;

import com.bojan.vending.dto.CoinDto;

public interface TransactionService {
    int deposit(CoinDto coinDto, String username);
}
