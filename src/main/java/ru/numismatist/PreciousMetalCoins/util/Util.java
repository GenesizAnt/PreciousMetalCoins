package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBException;
import ru.numismatist.PreciousMetalCoins.dto.CoinCollectorValue;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.models.SerializeableCoin;

public class Util {

//    public static String getXMLResponse(CoinXml coinXml) throws JAXBException {
//        return XmlSerializer.serializeCoinToXml(coinXml);
//    }
//
//    public static String getValueXMLResponse(CoinCollectorValue coinCollectorValue) throws JAXBException {
//        return XmlSerializer.serializeCoinCollectorValueToXml(coinCollectorValue);
//    }

    public static String getXMLResponse(SerializeableCoin coin) throws JAXBException {
        return XmlSerializer.serializeCoinToXml(coin);
    }
}
