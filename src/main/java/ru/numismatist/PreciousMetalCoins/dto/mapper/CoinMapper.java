package ru.numismatist.PreciousMetalCoins.dto.mapper;

import org.springframework.stereotype.Component;
import ru.numismatist.PreciousMetalCoins.dto.CoinCollectorValue;
import ru.numismatist.PreciousMetalCoins.dto.CoinToXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoinMapper {
    public CoinToXml toDtoXML(Coin coin) {
        return new CoinToXml(
                coin.getName(),
                coin.getDenomination(),
                coin.getMetal(),
                coin.getWeight(),
                coin.getCatalogNumber(),
                coin.getCost());
    }

    public List<CoinToXml> toDtoListXML(List<Coin> coins) {
        List<CoinToXml> coinToXmlList = new ArrayList<>();
        for (Coin coin : coins) {
            coinToXmlList.add(toDtoXML(coin));
        }
        return coinToXmlList;
    }

    public Coin toCoin(CoinToXml coinToXml) {
        return new Coin(
                coinToXml.getName(),
                coinToXml.getDenomination(),
                coinToXml.getMetal(),
                coinToXml.getWeight(),
                coinToXml.getCatalogNumber(),
                coinToXml.getCost());
    }

    public CoinCollectorValue toDtoCoinCollectorValue(Coin coin) {
        return new CoinCollectorValue(
                coin.getName(),
                coin.getMetal(),
                coin.getCatalogNumber(),
                coin.getCost(),
                coin.getWeight());
    }
}
