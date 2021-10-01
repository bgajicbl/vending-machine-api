package com.bojan.vending.service;

import com.bojan.vending.dto.BuyRequest;
import com.bojan.vending.dto.BuyResponse;
import com.bojan.vending.dto.CoinDto;

public interface TransactionService {
    int deposit(CoinDto coinDto, String username);

    BuyResponse buy(BuyRequest request, String username);

    CoinDto reset(String username);
}
