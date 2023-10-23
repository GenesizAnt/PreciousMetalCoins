package ru.numismatist.PreciousMetalCoins.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Coin")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoinXml { //возможно переименовать

    @XmlElement
    private String name;

    @XmlElement
    private int denomination;

    @XmlElement
    private String metal;

    @XmlElement
    private double weight;

    @XmlElement
    private String catalogNumber;

    @XmlElement
    private int cost;

    public CoinXml(String name, int denomination, String metal, double weight, String catalogNumber, int cost) {
        this.name = name;
        this.denomination = denomination;
        this.metal = metal;
        this.weight = weight;
        this.catalogNumber = catalogNumber;
        this.cost = cost;
    }

    public CoinXml() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
