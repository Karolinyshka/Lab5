package by.victoria.client;

import by.victoria.common.ConnectionTCP;
import by.victoria.entity.Command;
import by.victoria.entity.Country;
import by.victoria.entity.CountryProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Controller {
    private ConnectionTCP connectionTCP;
    private final ObservableList<CountryProperty> tableCountryProperties = FXCollections.observableArrayList();// вызовет конструктор 0

    @FXML
    private Button button_add;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_read;
    @FXML
    private Button button_update;
    @FXML
    private Button button_exit;
    @FXML
    private TableView<CountryProperty> table;
    @FXML
    private TableColumn<CountryProperty, Integer> column_id;
    @FXML
    private TableColumn<CountryProperty, String> column_capitalName;
    @FXML
    private TableColumn<CountryProperty, Integer> column_capitalPopulation;
    @FXML
    private TableColumn<CountryProperty, Double> column_capitalSquare;
    @FXML
    private TableColumn<CountryProperty, String> column_name;
    @FXML
    private TableColumn<CountryProperty, Integer> column_population;
    @FXML
    private TableColumn<CountryProperty, Double> column_square;
    @FXML
    private TextField field_capitalName;
    @FXML
    private TextField field_capitalPopulation;
    @FXML
    private TextField field_capitalSquare;
    @FXML
    private TextField field_name;
    @FXML
    private TextField field_population;
    @FXML
    private TextField field_square;

    @FXML
    public void initialize() {
        try {
            connectionTCP = new ConnectionTCP(new Socket("localhost", 6666));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        column_id.setCellValueFactory(cellValue -> cellValue.getValue().idProperty().asObject());
        column_name.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        column_population.setCellValueFactory(cellValue -> cellValue.getValue().populationProperty().asObject());
        column_square.setCellValueFactory(cellValue -> cellValue.getValue().squareProperty().asObject());
        column_capitalName.setCellValueFactory(cellValue -> cellValue.getValue().capitalNameProperty());
        column_capitalPopulation.setCellValueFactory(cellValue -> cellValue.getValue().capitalPopulationProperty().asObject());
        column_capitalSquare.setCellValueFactory(cellValue -> cellValue.getValue().capitalSquareProperty().asObject());

        button_read.setOnAction(event -> {
            tableCountryProperties.clear();// чтобы не добавлять каждый раз к существующему списку
            connectionTCP.writeObject(Command.READ);
            List<Country> countries = (List<Country>) connectionTCP.readObject();
            for (int i = 0; i < countries.size(); i++) {
                CountryProperty e = new CountryProperty(countries.get(i));
                tableCountryProperties.add(e);
            }
            table.setItems(tableCountryProperties);
        });
        button_add.setOnAction(event -> {
            try {
                String name = field_name.getText();
                double square = Double.parseDouble(field_square.getText());
                int population = Integer.parseInt(field_population.getText());
                String capitalName = field_capitalName.getText();
                double capitalSquare = Double.parseDouble(field_capitalSquare.getText());
                int capitalPopulation = Integer.parseInt(field_capitalPopulation.getText());

                if (name.isEmpty()||capitalName.isEmpty()){
                    throw new RuntimeException();
                }

                field_name.setText("");
                field_square.setText("");
                field_population.setText("");
                field_capitalName.setText("");
                field_capitalSquare.setText("");
                field_capitalPopulation.setText("");

                Country country = new Country(name,
                        square,
                        population,
                        capitalName,
                        capitalSquare,
                        capitalPopulation);

                connectionTCP.writeObject(Command.CREATE);
                connectionTCP.writeObject(country);
            } catch (RuntimeException e) {
                field_name.setText("input");
                field_square.setText("correct");
                field_population.setText("data");
            }
        });
        button_update.setOnAction(event -> {
            try {
                Country country = table.getSelectionModel().getSelectedItem().toCountry();

                String name = field_name.getText();
                if (!name.isEmpty()) {
                    country.setName(name);
                }
                String square = field_square.getText();
                if (!square.isEmpty()) {
                    country.setSquare(Double.parseDouble(square));
                }
                String population = field_population.getText();
                if (!population.isEmpty()) {
                    country.setPopulation(Integer.parseInt(population));
                }
                String capitalName = field_capitalName.getText();
                if (!capitalName.isEmpty()) {
                    country.setCapitalName(capitalName);
                }
                String capitalSquare = field_capitalSquare.getText();
                if (!capitalSquare.isEmpty()) {
                    country.setCapitalSquare(Double.parseDouble(capitalSquare));
                }
                String capitalPopulation = field_capitalPopulation.getText();
                if (!capitalPopulation.isEmpty()) {
                    country.setCapitalPopulation(Integer.parseInt(capitalPopulation));
                }

                field_name.setText("");
                field_square.setText("");
                field_population.setText("");
                field_capitalName.setText("");
                field_capitalSquare.setText("");
                field_capitalPopulation.setText("");

                connectionTCP.writeObject(Command.UPDATE);
                connectionTCP.writeObject(country);
            } catch (NullPointerException e) {
                field_name.setText("choose");
                field_square.setText("row");
            } catch (RuntimeException e) {
                field_name.setText("input");
                field_square.setText("correct");
                field_population.setText("data");
            }
        });
        button_delete.setOnAction(event -> {
            try {
                int id = table.getSelectionModel().getSelectedItem().getId();
                connectionTCP.writeObject(Command.DELETE);
                connectionTCP.writeObject(id);
            } catch (NullPointerException e) {// если 0
                field_name.setText("choose");
                field_square.setText("row");
            }
        });
        button_exit.setOnAction(event -> {
            connectionTCP.writeObject(Command.EXIT);
            connectionTCP.close();
            System.exit(0);
        });
    }
}

