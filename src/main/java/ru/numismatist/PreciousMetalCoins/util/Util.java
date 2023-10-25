package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBException;
import ru.numismatist.PreciousMetalCoins.dto.CoinCollectorValue;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;

public class Util {

    public static String getXMLResponse(CoinXml coinXml) throws JAXBException {
        return XmlSerializer.serializeCoinToXml(coinXml);
    }

    public static String getValueXMLResponse(CoinCollectorValue coinCollectorValue) throws JAXBException {
        return XmlSerializer.serializeCoinCollectorValueToXml(coinCollectorValue);
    }
}
