package by.victoria.entity;

import java.io.Serializable;

public class Country implements Serializable {
    private int id;
    private String name;
    private double square;
    private int population;
    private String capitalName;
    private double capitalSquare;
    private int capitalPopulation;

    public Country(String name,  // когда клиент присылает информацию
                   double square,
                   int population,
                   String capitalName,
                   double capitalSquare,
                   int capitalPopulation) {
        this.name = name;
        this.square = square;
        this.population = population;
        this.capitalName = capitalName;
        this.capitalSquare = capitalSquare;
        this.capitalPopulation = capitalPopulation;
    }
    public Country(int id,    //когда читаем из БД
                   String name,
                   double square,
                   int population,
                   String capitalName,
                   double capitalSquare,
                   int capitalPopulation) {
        this.id = id;
        this.name = name;
        this.square = square;
        this.population = population;
        this.capitalName = capitalName;
        this.capitalSquare = capitalSquare;
        this.capitalPopulation = capitalPopulation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSquare() {
        return square;
    }

    public int getPopulation() {
        return population;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public double getCapitalSquare() {
        return capitalSquare;
    }

    public int getCapitalPopulation() {
        return capitalPopulation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public void setCapitalSquare(double capitalSquare) {
        this.capitalSquare = capitalSquare;
    }

    public void setCapitalPopulation(int capitalPopulation) {
        this.capitalPopulation = capitalPopulation;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", square=" + square +
                ", population=" + population +
                ", capitalName='" + capitalName + '\'' +
                ", capitalSquare=" + capitalSquare +
                ", capitalPopulation=" + capitalPopulation +
                '}';
    }
}
