package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryPersistence {
    public static List<Country> getAllCountries() throws Exception {
        List<Country> countryList = new ArrayList<Country>();
        String JDBCPostgreSQLConnectionString = "jdbc:postgresql://db-server:5432/postgres?user=postgres";
        String countriesQuery = "SELECT name, code, capital, province, area, population from country";

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countriesQuery)) {
            while (resultSet.next()) {
                Country country = new Country();
                country.setName(resultSet.getString("name"));
                country.setCode(resultSet.getString("code"));
                country.setCapital(resultSet.getString("capital"));
                country.setProvince(resultSet.getString("province"));
                country.setArea(resultSet.getBigDecimal("area"));
                country.setPopulation(resultSet.getBigDecimal("population"));
                countryList.add(country);
            }
        }

        return countryList;
    }
}
