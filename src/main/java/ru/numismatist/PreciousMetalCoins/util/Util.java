package ru.numismatist.PreciousMetalCoins.util;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;

import java.io.IOException;
import java.io.PrintWriter;

public class Util {

    public static String getXMLResponse(CoinXml coinXml) throws JAXBException {
        return XmlSerializer.serializeObjectToXml(coinXml);
    }

}
