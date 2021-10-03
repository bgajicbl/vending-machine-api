package com.bojan.vending.dto.mapper;

import com.bojan.vending.dto.CoinDto;
import org.springframework.stereotype.Component;

@Component
public class CoinMapper {

    public static int calculateTotal(CoinDto coin) {
        int total = 0;
        total += 5 * coin.getNickel();
        total += 10 * coin.getDime();
        total += 20 * coin.getFifth();
        total += 50 * coin.getHalf();
        total += 100 * coin.getDollar();
        return total;

    }

}
