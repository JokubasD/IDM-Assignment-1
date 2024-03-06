package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class BgtDataManagerJDBC implements BgtDataManager {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Boardgame";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";

    // JDBC connection
    private Connection connection;

    public BgtDataManagerJDBC() {
        try {
            connection = getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("connected successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Player ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "nickname VARCHAR(255) NOT NULL"
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Player table created successfully (if it didn't already exist).");
            try(
                    PreparedStatement statement2 = connection.prepareStatement("INSERT INTO player (name, nickname) " +
                            "VALUES (?, ?)")) {
                statement2.setString(1, name);
                statement2.setString(2, nickname);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PlayerJDBC(name, nickname);
    }

    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        Collection<Player> players = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM player" +
                " WHERE name LIKE ? ")) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String playerName = resultSet.getString("name");
                    String nickname = resultSet.getString("nickname");
                    players.add(new PlayerJDBC(playerName, nickname));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
        }
    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Boardgame ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "bggURL VARCHAR(255) NOT NULL"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Boardgame table created successfully (if it didn't already exist).");
            try(
                    PreparedStatement statement2 = connection.prepareStatement("INSERT INTO boardgame (name, bggurl) " +
                            "VALUES (?, ?)")) {
                statement2.setString(1, name);
                statement2.setString(2, bggURL);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BoardGameJDBC(name, bggURL);
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        Collection<BoardGame> boardgames = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                     "FROM boardgame" +
                     " WHERE name LIKE ? ")) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    BoardGameJDBC game = new BoardGameJDBC(resultSet.getString("name"), resultSet.getString("bggurl"));
                    boardgames.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardgames;
    }

    @Override
    public void persistPlayer(Player player) {

    }

    @Override
    public void persistBoardGame(BoardGame game) {

    }
    // DO NOT NEED TO IMPLEMENT FOR JDBC
    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        return null; //Do not need to implement
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        return null; //Do not need to implement
    }
    @Override
    public void persistPlaySession(PlaySession session) {
        //Do not need to implement
    }


    // Don't forget to handle closing connections and other resources properly
}