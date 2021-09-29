package com.bojan.vending.repository;

import com.bojan.vending.model.Coin;
import com.bojan.vending.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    Optional<Coin> findByUser(User user);
}