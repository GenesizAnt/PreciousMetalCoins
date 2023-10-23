package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;

import java.io.StringWriter;

public class XmlSerializer {

    public static String serializeObjectToXml(CoinXml coinXml) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(coinXml.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(coinXml, stringWriter);

        return stringWriter.toString();
    }

}
