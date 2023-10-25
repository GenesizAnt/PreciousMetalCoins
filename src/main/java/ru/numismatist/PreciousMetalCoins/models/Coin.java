package ru.numismatist.PreciousMetalCoins.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Coins")
@XmlRootElement
public class Coin implements SerializableCoin {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "name")
    private String name;

    @Positive
    @Column(name = "denomination")
    private int denomination;

    @NotEmpty(message = "Металл не должен быть пустым")
    @Size(min = 2, max = 100, message = "Название металла должно быть от 2 до 100 символов")
    @Column(name = "metal")
    private String metal;

    @Positive
    @Column(name = "weight")
    private double weight;

    @NotEmpty(message = "Каталожный номер не должен быть пустым")
    @Size(min = 2, max = 100, message = "Каталожный номер должен быть от 2 до 100 символов")
    @Column(name = "catalogNumber")
    private String catalogNumber;

    @Positive
    @Column(name = "cost")
    private int cost;

    public Coin(String name, int denomination, String metal, double weight, String catalogNumber, int cost) {
        this.name = name;
        this.denomination = denomination;
        this.metal = metal;
        this.weight = weight;
        this.catalogNumber = catalogNumber;
        this.cost = cost;
    }

    public Coin() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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