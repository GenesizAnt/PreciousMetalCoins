package ru.numismatist.PreciousMetalCoins.dto.mapper;

import org.springframework.stereotype.Component;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoinMapper {

    public CoinXml toDtoXML(Coin coin) {

        return new CoinXml(
                coin.getName(),
                coin.getDenomination(),
                coin.getMetal(),
                coin.getWeight(),
                coin.getCatalogNumber(),
                coin.getCost());
    }

    public List<CoinXml> toDtoListXML(List<Coin> coins) {
        List<CoinXml> coinXmlList = new ArrayList<>();
        for (Coin coin : coins) {
            coinXmlList.add(toDtoXML(coin));
        }
        return coinXmlList;
    }

    public Coin toCoin(CoinXml coinXml) {
        return new Coin(
                coinXml.getName(),
                coinXml.getDenomination(),
                coinXml.getMetal(),
                coinXml.getWeight(),
                coinXml.getCatalogNumber(),
                coinXml.getCost());
    }
}
