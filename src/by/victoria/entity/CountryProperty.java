package by.victoria.entity;

import javafx.beans.property.*;

public class CountryProperty {
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty square;
    private IntegerProperty population;
    private StringProperty capitalName;
    private DoubleProperty capitalSquare;
    private IntegerProperty capitalPopulation;

    public CountryProperty(Country country) {
        id = new SimpleIntegerProperty(country.getId());
        name = new SimpleStringProperty(country.getName());
        square = new SimpleDoubleProperty(country.getSquare());
        population = new SimpleIntegerProperty(country.getPopulation());
        capitalName = new SimpleStringProperty(country.getCapitalName());
        capitalSquare = new SimpleDoubleProperty(country.getCapitalSquare());
        capitalPopulation = new SimpleIntegerProperty(country.getCapitalPopulation());
    }

    public Country toCountry() {
        return new Country(id.intValue(),
                name.getValue(),
                square.doubleValue(),
                population.intValue(),
                capitalName.getValue(),
                capitalSquare.doubleValue(),
                capitalPopulation.intValue());
    }

    public int getId() {
        return id.get();
    }
// они используются неявно
    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getSquare() {
        return square.get();
    }

    public DoubleProperty squareProperty() {
        return square;
    }

    public int getPopulation() {
        return population.get();
    }

    public IntegerProperty populationProperty() {
        return population;
    }

    public String getCapitalName() {
        return capitalName.get();
    }

    public StringProperty capitalNameProperty() {
        return capitalName;
    }

    public double getCapitalSquare() {
        return capitalSquare.get();
    }

    public DoubleProperty capitalSquareProperty() {
        return capitalSquare;
    }

    public int getCapitalPopulation() {
        return capitalPopulation.get();
    }

    public IntegerProperty capitalPopulationProperty() {
        return capitalPopulation;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSquare(double square) {
        this.square.set(square);
    }

    public void setPopulation(int population) {
        this.population.set(population);
    }

    public void setCapitalName(String capitalName) {
        this.capitalName.set(capitalName);
    }

    public void setCapitalSquare(double capitalSquare) {
        this.capitalSquare.set(capitalSquare);
    }

    public void setCapitalPopulation(int capitalPopulation) {
        this.capitalPopulation.set(capitalPopulation);
    }
}
