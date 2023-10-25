package ru.numismatist.PreciousMetalCoins.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.util.ResourceUtils;
import ru.numismatist.PreciousMetalCoins.dto.CoinCollectorValue;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.models.SerializeableCoin;

import java.io.*;
import java.util.stream.Collectors;

public class XmlSerializer {

    private static File file;

    static {
        try {
            file = ResourceUtils.getFile("CoinXML.xml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    public static String serializeCoinToXml(CoinXml coinXml) throws JAXBException {
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(coinXml.getClass());
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        StringWriter stringWriter = new StringWriter();
//        marshaller.marshal(coinXml, stringWriter);
//
//        return stringWriter.toString();
//    }
//
//    public static String serializeCoinCollectorValueToXml(CoinCollectorValue coinCollectorValue) throws JAXBException {
//
//        JAXBContext jaxbContext = JAXBContext.newInstance(coinCollectorValue.getClass());
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        StringWriter stringWriter = new StringWriter();
//        marshaller.marshal(coinCollectorValue, stringWriter);
//
//        return stringWriter.toString();
//    }

    public static String serializeCoinToXml(SerializeableCoin coin) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(coin.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(coin, stringWriter);

        return stringWriter.toString();
    }

    public static Coin convertXMLToCoin() {
        Coin coin = new Coin();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Coin.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (Coin) unmarshaller.unmarshal(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return coin;
    }
}

