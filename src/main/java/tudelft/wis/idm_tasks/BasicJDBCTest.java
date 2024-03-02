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
                List<String> jobs = new ArrayList<>();
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT t.primary_title, t.title_id , ci.job_category " +
                             "FROM titles t " +
                             "JOIN cast_info ci ON t.title_id = ci.title_id " +
                             "WHERE t.primary_title LIKE ? LIMIT 20")) {
                    statement.setString(1, "%" + searchString + "%");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            jobs.add(resultSet.getString("job_category"));
                        }
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return jobs;
            }

            @Override
            public double getAverageRuntimeOfGenre(String genre) {
                // Implement method to calculate average runtime of a genre
                return 0; // Placeholder return value
            }

            @Override
            public Collection<String> getPlayedCharacters(String actorFullname) {
                List<String> titles = new ArrayList<>();
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement("SELECT tpc.character_name " +
                             "FROM persons p " +
                             "JOIN title_person_character tpc ON p.person_id = tpc. person_id " +
                             "WHERE p.full_name = ?" +
                             " LIMIT 20")) {
                    statement.setString(1, actorFullname);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            titles.add(resultSet.getString("character_name"));
                        }
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return titles;
            }
        };

        try {
            Connection connection = jdbcTask2Interface.getConnection();
            System.out.println("Connected to the database.");
            Collection<String> query1 = jdbcTask2Interface.getTitlesPerYear(2022);
            System.out.println("Query 1 - First 20 titles that were released in 2022: ");
            printResults(query1);
            Collection<String> query4 = jdbcTask2Interface.getPlayedCharacters("Brad Pitt");
            System.out.println("Query 4 - All characters that are played by Brad Pitt: ");
            printResults(query4);
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void printResults(Collection<String> items){
        System.out.println(String.join(", ", items));
    }

}
