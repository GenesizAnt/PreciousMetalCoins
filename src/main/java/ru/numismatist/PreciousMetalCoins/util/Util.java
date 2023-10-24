package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBException;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;

public class Util {

    public static String getXMLResponse(CoinXml coinXml) throws JAXBException {
        return XmlSerializer.serializeCoinToXml(coinXml);
    }

}
