package ru.numismatist.PreciousMetalCoins.models;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
    @XmlAttribute(name = "Date")
    private String date;
    @XmlAttribute(name = "Code")
    private String code;
    @XmlElement(name = "Buy")
    private String buy;
    @XmlElement(name = "Sell")
    private String sell;

    public Record() {
    }

    public Record(String date, String code, String buy, String sell) {
        this.date = date;
        this.code = code;
        this.buy = buy;
        this.sell = sell;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
