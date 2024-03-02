package tudelft.wis.idm_tasks;

import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;
import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCTask2Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class BasicJDBCTest {

    public static void main(String[] args) {
        JDBCTask2Interface jdbcTask2Interface = new JDBCTask2Interface() {
            private static final String URL = "jdbc:postgresql://localhost:5432/imdb";
            private static final String USERNAME = "postgres";
            private static final String PASSWORD = "Sanoma1705";
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("org.postgresql.Driver");

                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }

            @Override
            public Collection<String> getTitlesPerYear(int year) {
                return null;
            }

            @Override
            public Collection<String> getJobCategoriesFromTitles(String searchString) {
                return null;
            }

            @Override
            public double getAverageRuntimeOfGenre(String genre) {
                System.out.println("The genre you entered is: " + genre);
                return 0;
            }

            @Override
            public Collection<String> getPlayedCharacters(String actorFullname) {
                return null;
            }
        };

        try {
            Connection connection = jdbcTask2Interface.getConnection();
            System.out.println("Connected to the database.");
            jdbcTask2Interface.getAverageRuntimeOfGenre("Horror");
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
