package com.bojan.vending.dto.mapper;

import com.bojan.vending.dto.CoinDto;
import com.bojan.vending.model.Coin;
import org.springframework.stereotype.Component;

@Component
public class CoinMapper {

    public static CoinDto toCoinDto(Coin coin){
        return CoinDto.builder()
                .nickel(coin.getNickel())
                .dime(coin.getDime())
                .fifth(coin.getFifth())
                .half(coin.getHalf())
                .dollar(coin.getDollar())
                //.userId(coin.getUser().getId())
                //.total(calculateTotal(coin))
                .build();
    }

    public static Coin toCoin(CoinDto coinDto){
        return Coin.builder()
                .nickel(coinDto.getNickel())
                .dime(coinDto.getDime())
                .fifth(coinDto.getFifth())
                .half(coinDto.getHalf())
                .dollar(coinDto.getDollar())
                .build();
    }

    public static Coin addCoins(Coin current, Coin deposit){
        current.setNickel(current.getNickel() + deposit.getNickel());
        current.setDime(current.getDime() + deposit.getDime());
        current.setFifth(current.getFifth() + deposit.getFifth());
        current.setHalf(current.getHalf() + deposit.getHalf());
        current.setDollar(current.getDollar() + deposit.getDollar());
        return current;
    }

    public static int calculateTotal(CoinDto coin){
        int total = 0;
        total += 5 * coin.getNickel();
        total += 10 * coin.getDime();
        total += 20 * coin.getFifth();
        total += 50 * coin.getHalf();
        total += 100 * coin.getDollar();
        return total;
    }
}
