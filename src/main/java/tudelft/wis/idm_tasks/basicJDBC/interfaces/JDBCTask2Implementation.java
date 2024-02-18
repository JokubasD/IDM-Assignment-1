package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

// Imports needed for the JDBC API
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Solution to Task 2.1
 * @author Alexandra Neagu
  */
public class JDBCTask2Implementation implements JDBCTask2Interface {

    // Connection to the database object
    private Connection connection;

    // Credentials needed to establish connection to the DB
    private final String URL, user, password;


    /**
     * Instantiates a new JDBC Task 2 implementation.
     *
     * @param user     The username
     * @param password The password
     * @param DBName   The database name
     */
    public JDBCTask2Implementation(String user, String password, String DBName) {
        this.user = user;
        this.password = password;
        // Change 5432 in the URL to the port you installed PostgreSQL on, if it wasn't 5432 by default
        this.URL = "jdbc:postgresql://localhost:5432/" + DBName;
        this.connection = getConnection();
    }

    /**
     * Establishes the connection to the PostgreSQL database.
     * @return The connection object. If a connection couldn't be established, returns null
     */
    @Override
    public Connection getConnection() {
        // Load the driver class
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to load the class. Terminating the program");
            ex.printStackTrace();
        }
        // Get the connection
        try {
            // Set postgres properties
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            connection = DriverManager.getConnection(URL, props);
            return connection;
        } catch (SQLException ex) {
            System.out.println("Error getting connection: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        // If a connection to the DB couldn't be established, the returned connection object is null
        return null;
    }

    /**
     * Lists all the primary titles for a specific start year.
     * @param year A specific start year to query the titles on
     * @return A collection of strings of the resulting primary titles
     */
    @Override
    public Collection<String> getTitlesPerYear(int year) {
        Collection<String> titles = new ArrayList<>();

        String query = "SELECT primary_title FROM titles WHERE start_year = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                titles.add(resultSet.getString("primary_title"));
            }
            // Close the resources used
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }

    /**
     * Lists all the job categories for titles that include a specific string in their primary title.
     * @param searchString A string that will be used to filter the primary titles on
     * @return A collection of strings of the resulting job categories
     */
    @Override
    public Collection<String> getJobCategoriesFromTitles(String searchString) {
        Collection<String> jobCategories = new ArrayList<>();

        String query = "SELECT DISTINCT job_category FROM cast_info ci JOIN titles t ON ci.title_id = t.title_id WHERE t.primary_title LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + searchString + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                jobCategories.add(resultSet.getString("job_category"));
            }
            // Close the resources used
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobCategories;
    }

    /**
     * Lists the average runtime of a specified genre.
     * @param genre A string that specifies the genre to be queried on
     * @return A double corresponding to the average runtime of the specified genre
     */
    @Override
    public double getAverageRuntimeOfGenre(String genre) {
        // The average runtime is initialized to 0 in case the genre that is passed as parameter doesn't exist in the DB.
        // In that case, the avg_runtime returned would be null, but a double variable cannot be null.
        double averageRuntime = 0;

        String query = "SELECT AVG(runtime) AS avg_runtime FROM titles t JOIN titles_genres tg ON t.title_id = tg.title_id WHERE tg.genre = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                averageRuntime = resultSet.getDouble("avg_runtime");
            }
            // Close the resources used
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return averageRuntime;
    }

    /**
     * Given a person's full name, lists all the characters they have played.
     * @param actorFullname A string of the person's full name
     * @return A collection of strings corresponding to the character names the provided person has played
     */
    @Override
    public Collection<String> getPlayedCharacters(String actorFullname) {
        Collection<String> characters = new ArrayList<>();

        String query = "SELECT character_name FROM title_person_character tpc JOIN persons p ON tpc.person_id = p.person_id WHERE p.full_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, actorFullname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                characters.add(resultSet.getString("character_name"));
            }
            // Close the resources used
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return characters;
    }

    /**
     * Main function of the class. You can play around with it to run the queries, print the returned results to the console for verification, etc.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        // Create the connection
        // Put your credentials (user, password, DBName) here
        JDBCTask2Implementation util = new JDBCTask2Implementation("yourUser", "yourPassword", "yourDBName");

        // Run a query example
        // double avgRuntime = util.getAverageRuntimeOfGenre("Adult");
        // You can print the results to the console for verification: System.out.print(avgRuntime);
    }
}
