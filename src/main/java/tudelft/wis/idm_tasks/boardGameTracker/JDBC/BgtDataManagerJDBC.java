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
    private static final String PASSWORD = "JoringisBE2023!";

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
    public void persistPlayer(Player player1) throws BgtException {
        PlayerJDBC player = (PlayerJDBC) player1;
        try {
            // Check if the player already exists in the database
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Player WHERE name = ?")) {
                statement.setString(1, player.getPlayerName());
                ResultSet set = statement.executeQuery();

                if (set != null && set.next()) {
                    // Player exists, update their nickname
                    String updatePlayerSQL = "UPDATE Player SET nickname = ? WHERE name = ?";
                    try (PreparedStatement updatePlayerStatement = connection.prepareStatement(updatePlayerSQL)) {
                        updatePlayerStatement.setString(1, player.getPlayerNickName());
                        updatePlayerStatement.setString(2, player.getPlayerName());
                        int playerRowsChanged = updatePlayerStatement.executeUpdate();
                        if (playerRowsChanged > 0) {
                            System.out.println("Player updated successfully.");
                        } else {
                            System.out.println("No player found with the specified name.");
                        }
                    }
                } else {
                    // Player does not exist, create a new player
                    String insertPlayerSQL = "INSERT INTO Player (name, nickname) VALUES (?, ?)";
                    try (PreparedStatement insertPlayerStatement = connection.prepareStatement(insertPlayerSQL)) {
                        insertPlayerStatement.setString(1, player.getPlayerName());
                        insertPlayerStatement.setString(2, player.getPlayerNickName());
                        insertPlayerStatement.executeUpdate();
                    }
                }
            }

            // Insert or update the player's board games if present
            Collection<BoardGame> boardGames = player.getGameCollection();
            if (!boardGames.isEmpty()) {
                String insertGameSQL = "INSERT INTO Player_BoardGames (player_id, game_id) VALUES (?, ?)";
                try (PreparedStatement insertGameStatement = connection.prepareStatement(insertGameSQL)) {
                    for (BoardGame game : boardGames) {
                        insertGameStatement.setInt(1, getPlayerId(player.getPlayerName()));
                        insertGameStatement.setInt(2, getGameId(game.getName()));
                        insertGameStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void persistBoardGame(BoardGame game) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM boardgame" +
                " WHERE name = ? ")) {
            statement.setString(1, game.getName());
            ResultSet set = statement.executeQuery();
            if (set != null && set.next()) {
                String updateSQL = "UPDATE boardgame SET bggurl = ? WHERE name = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)){
                    updateStatement.setString(1, game.getBGG_URL());
                    updateStatement.setString(2, game.getName());
                    int rowsChanged = updateStatement.executeUpdate();
                    if (rowsChanged > 0) {
                        System.out.println("Game updated successfully.");
                    } else {
                        System.out.println("No game found with the specified name.");
                    }
                }
            } else {
                createNewBoardgame(game.getName(), game.getBGG_URL());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (BgtException e) {
            throw new RuntimeException(e);
        }
    }
    // DO NOT NEED TO IMPLEMENT FOR JDBC
    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        String insertSessionSQL = "INSERT INTO PlaySession (date, host_id, game_id, playtime, winner_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertSessionStatement = connection.prepareStatement(insertSessionSQL)) {
            insertSessionStatement.setDate(1, new java.sql.Date(date.getTime()));
            insertSessionStatement.setInt(2, getPlayerId(host.getPlayerName()));
            insertSessionStatement.setInt(3, getGameId(game.getName()));
            insertSessionStatement.setInt(4, playtime);
            insertSessionStatement.setInt(5, getPlayerId(winner.getPlayerName()));
            insertSessionStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BgtException();
        }
        return new PlaySessionJDBC(date, host, game, playtime, players, winner);
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
    //no need
        return null;
    }
    @Override
    public void persistPlaySession(PlaySession session) {
        //Do not need to implement
    }


    // Don't forget to handle closing connections and other resources properly
    private int getPlayerId(String playerName) throws SQLException {
        String query = "SELECT id FROM Player WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        throw new SQLException("Player not found: " + playerName);
    }

    // Helper method to get the game's ID from the database
    private int getGameId(String gameName) throws SQLException {
        String query = "SELECT id FROM BoardGame WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, gameName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        throw new SQLException("Board game not found: " + gameName);
    }
}