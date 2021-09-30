package com.bojan.vending.controller;

import com.bojan.vending.dto.BuyRequest;
import com.bojan.vending.dto.BuyResponse;
import com.bojan.vending.dto.CoinDto;
import com.bojan.vending.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "transaction", description = "The Transaction API")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @RolesAllowed("BUYER")
    @Operation(summary = "Deposit new funds", description = "Deposit new funds", tags = { "transaction" })
    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public int deposit(@NotNull @Valid @RequestBody CoinDto coinDto, Principal principal){
        String username = principal != null ? principal.getName() : null;
        return transactionService.deposit(coinDto, username);
    }

    @RolesAllowed("BUYER")
    @Operation(summary = "Buy products", description = "Buy products", tags = { "transaction" })
    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public BuyResponse buy(@NotNull @Valid @RequestBody BuyRequest request, Principal principal){
        String username = principal != null ? principal.getName() : null;
        return transactionService.buy(request, username);
    }

}
