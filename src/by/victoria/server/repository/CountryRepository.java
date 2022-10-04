package by.victoria.server.repository;

import by.victoria.entity.Country;
import by.victoria.server.database.DatabaseConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository {
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = DatabaseConnectionProvider.getConnection();// если в скобках что-то указывается, то потом вызовется метод close()
             Statement statement = connection.createStatement()) { //выполняет запрос

            ResultSet resultSet = statement.executeQuery("select * from country");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double square = resultSet.getDouble("square");
                int population = resultSet.getInt("population");
                String capitalName = resultSet.getString("capitalName");
                double capitalSquare = resultSet.getDouble("capitalSquare");
                int capitalPopulation = resultSet.getInt("capitalPopulation");

                Country country = new Country(id, name, square, population, capitalName, capitalSquare, capitalPopulation);

                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public void addCountry(Country country) {
        try (Connection connection = DatabaseConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(// позволяет вставлять значения
                     "insert into country(name, square, population, capitalName, capitalSquare, capitalPopulation) " +
                             "value(?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, country.getName()); // Ps позволяет нам вставить параметры
                                                                            // в запрос, выполняется командой execute,
                                                                           // это класс, который выполнит этот запрос через бд. Ы
            preparedStatement.setDouble(2, country.getSquare());
            preparedStatement.setInt(3, country.getPopulation());
            preparedStatement.setString(4, country.getCapitalName());
            preparedStatement.setDouble(5, country.getCapitalSquare());
            preparedStatement.setInt(6, country.getCapitalPopulation());

            preparedStatement.execute();//выполняем запрос

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCountry(Country country) {
        try (Connection connection = DatabaseConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update country set name = ?, square = ?, population = ?, capitalName = ?, " +
                             "capitalSquare = ?, capitalPopulation = ? where id = ?")) {

            preparedStatement.setString(1, country.getName());
            preparedStatement.setDouble(2, country.getSquare());
            preparedStatement.setInt(3, country.getPopulation());
            preparedStatement.setString(4, country.getCapitalName());
            preparedStatement.setDouble(5, country.getCapitalSquare());
            preparedStatement.setInt(6, country.getCapitalPopulation());
            preparedStatement.setInt(7, country.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCountryByID(int id) {
        try (Connection connection = DatabaseConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from country where id = ?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
