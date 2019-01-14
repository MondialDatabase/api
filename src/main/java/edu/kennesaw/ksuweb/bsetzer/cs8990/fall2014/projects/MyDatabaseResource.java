package edu.kennesaw.ksuweb.bsetzer.cs8990.fall2014.projects;

import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mydatabaseresource")
public class MyDatabaseResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getSomethingFromDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to connect to database.";
        }

        String response = "";
        String JDBCPostgreSQLConnectionString = "jdbc:postgresql://db-server:5432/postgres?user=postgres";

        try {
            Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from country");

            while (resultSet.next()) {
                response += resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response = "Failed to execute query";
        }

        return response;
    }
}