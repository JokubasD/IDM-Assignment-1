package tudelft.wis.idm_tasks.boardGameTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardGameSetup {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Boardgame";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";

    // JDBC connection
    private Connection connection;

    public BoardGameSetup() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connected successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayertable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Player ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "nickname VARCHAR(255) NOT NULL"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Player table created successfully (if it didn't already exist).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createBoardgame() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Boardgame ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "bggURL VARCHAR(255) NOT NULL"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Boardgame table created successfully (if it didn't already exist).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
