package ru.numismatist.PreciousMetalCoins.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.models.PreciousMetal;
import ru.numismatist.PreciousMetalCoins.models.SerializeableCoin;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ru.numismatist.PreciousMetalCoins.models.PreciousMetal.*;

@XmlRootElement(name = "CoinCollectorValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoinCollectorValue implements SerializeableCoin {

    @XmlElement
    private String name;

    @XmlElement
    private String metal;

    private String catalogNumber;

    @XmlElement
    private BigDecimal collectorValueRub;

    @XmlElement
    private int cost;

    private double weight;

    private PreciousMetal preciousMetalFromCatalogNumber;

    public CoinCollectorValue(String name, String metal, String catalogNumber, int cost, double weight) {
        this.name = name;
        this.metal = metal;
        this.catalogNumber = catalogNumber;
        this.cost = cost;
        this.weight = weight;
        this.preciousMetalFromCatalogNumber = parserMetalCode(catalogNumber);
    }

    public CoinCollectorValue() {

    }

    private PreciousMetal parserMetalCode(String catalogNumber) {
        return switch (Integer.parseInt(catalogNumber.substring(1, 2))) {
            case 1 -> SILVER;
            case 2 -> GOLD;
            case 3 -> PLATINUM;
            case 4 -> PALLADIUM;
            default -> throw new IllegalStateException("Unexpected value: " + Integer.parseInt(catalogNumber.substring(1, 2)) +
                    ". value 1 - gold, 2 - silver, 3 - platinum, 4 - palladium");
        };
    }

    public void calculateCollectorValueRub(String collectorValueRub) {
        BigDecimal pricePreciousMetal = new BigDecimal(collectorValueRub.replace(",", "."));
        BigDecimal costThisCoin = new BigDecimal(getCost());
        this.collectorValueRub = costThisCoin.subtract(pricePreciousMetal.multiply(BigDecimal.valueOf(weight))).
                setScale(2, RoundingMode.HALF_EVEN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public PreciousMetal getPreciousMetalFromCatalogNumber() {
        return preciousMetalFromCatalogNumber;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public BigDecimal getCollectorValueRub() {
        return collectorValueRub;
    }
}
