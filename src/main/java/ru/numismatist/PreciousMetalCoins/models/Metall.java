package ru.numismatist.PreciousMetalCoins.models;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "Metall")
@XmlAccessorType(XmlAccessType.FIELD)
public class Metall {
    @XmlAttribute(name = "FromDate")
    private String fromDate;
    @XmlAttribute(name = "ToDate")
    private String toDate;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "Record")
    private List<Record> records;

    public Metall() {
    }

    public Metall(String fromDate, String toDate, String name, List<Record> records) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.name = name;
        this.records = records;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
