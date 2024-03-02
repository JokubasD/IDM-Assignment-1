package tudelft.wis.idm_tasks;

import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;
import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCTask2Interface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasicJDBCTest {
    public static void main(String[] args) {
        JDBCTask2Interface jdbcTask2Interface = new JDBCTask2Interface() {
            private static final String URL = "jdbc:postgresql://localhost:5432/imdb";
            private static final String USERNAME = "postgres";
            private static final String PASSWORD = "";

            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }

            @Override
            public Collection<String> getTitlesPerYear(int year) {
                List<String> titles = new ArrayList<>();
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement("SELECT primary_title FROM titles WHERE start_year = ? LIMIT 20")) {
                    statement.setInt(1, year);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            titles.add(resultSet.getString("primary_title"));
                        }
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return titles;
            }

            @Override
            public Collection<String> getJobCategoriesFromTitles(String searchString) {
                // Implement method to retrieve job categories from titles in the database
                return null;
            }

            @Override
            public double getAverageRuntimeOfGenre(String genre) {
                // Implement method to calculate average runtime of a genre
                return 0; // Placeholder return value
            }

            @Override
            public Collection<String> getPlayedCharacters(String actorFullname) {
                // Implement method to retrieve played characters by an actor
                return null;
            }
        };

        try {
            Connection connection = jdbcTask2Interface.getConnection();
            System.out.println("Connected to the database.");
            Collection<String> printable = jdbcTask2Interface.getTitlesPerYear(2022);
            for (String title : printable) {
                System.out.println(title);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
