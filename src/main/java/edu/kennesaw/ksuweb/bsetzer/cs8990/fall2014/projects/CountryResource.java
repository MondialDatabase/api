package edu.kennesaw.ksuweb.bsetzer.cs8990.fall2014.projects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("country")
public class CountryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> get() throws Exception {
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