package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBException;
import ru.numismatist.PreciousMetalCoins.models.SerializableCoin;

public class Util {
    public static String getXMLResponse(SerializableCoin coin) throws JAXBException {
        return XmlSerializer.serializeCoinToXml(coin);
    }
}
