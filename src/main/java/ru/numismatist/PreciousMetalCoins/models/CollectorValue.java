package ru.numismatist.PreciousMetalCoins.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement(name = "CollectorValue")
public class CollectorValue {

//    private BigDecimal silverPricePerGramRUB = BigDecimal.valueOf(69.71); //цена на 25.10 https://cbr.ru/hd_base/metall/metall_base_new/

    @XmlElement
    private String name;

    @XmlElement
    private String metal;

    @XmlElement
    private BigDecimal silverPricePerGramRub = BigDecimal.valueOf(69.71); //цена на 25.10 https://cbr.ru/hd_base/metall/metall_base_new/

    @XmlElement
    private int collectorValueRub;

    public CollectorValue(String name, String metal, BigDecimal silverPricePerGramRub, int collectorValueRub) {
        this.name = name;
        this.metal = metal;
        this.silverPricePerGramRub = silverPricePerGramRub;
        this.collectorValueRub = collectorValueRub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public BigDecimal getSilverPricePerGramRub() {
        return silverPricePerGramRub;
    }

    public void setSilverPricePerGramRub(BigDecimal silverPricePerGramRub) {
        this.silverPricePerGramRub = silverPricePerGramRub;
    }

    public int getCollectorValueRub() {
        return collectorValueRub;
    }

    public void setCollectorValueRub(int collectorValueRub) {
        this.collectorValueRub = collectorValueRub;
    }
}
