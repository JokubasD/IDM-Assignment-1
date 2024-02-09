package tudelft.wis.idm_solutions.BoardGameTracker;

import com.github.javafaker.Faker;
import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

public abstract class AbstractBGTDemo implements JDBCManager {

    private static Connection connection;
    private static Faker faker = new Faker();
    private static final Random RND = new Random();

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:duckdb:./DB/bggt.duckdb");

        };
        return connection;
    }

    /**
     * Returns a random subset of the specified collection of requested size.
     *
     * @param <T> Type parameter.
     * @param c Collection to pick from.
     * @param count Requested size of the subset.
     * @return List of randomly picked elements of the collection.
     */
    public static <T> List<T> rndSubset(final Collection<T> c, final int count) {
        if (c == null) {
            throw new IllegalArgumentException("Expecting non-null parameter");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Size of the subset cannot be negative: " + count);
        }
        if (count > c.size()) {
            throw new IllegalArgumentException("Subset cannot be bigger than the original set: " + count);
        }

        final List<T> ret = new ArrayList<T>(count);
        final List<T> tmp = new ArrayList<T>(c);

        for (int i = 0; i < count; i++) {
            final int toRemove = RND.nextInt(tmp.size());
            ret.add(tmp.get(toRemove));
            tmp.remove(toRemove);
        }

        return ret;
    }

    /**
     * Returns an instance of BgtDataFactory which can create new BGT objects.
     *
     * @return
     */
    public abstract BgtDataManager getBgtDataManager();

    /**
     * Creates several players and games, and adds them to the DB.
     *
     * @return
     */
    public Collection<PlaySession> createDummyData(int numOfPlayers, int numOfSessions) throws SQLException {
        Collection<PlaySession> sessions = new LinkedList<PlaySession>();
        Collection<Player> players = new LinkedList<Player>();
        Collection<BoardGame> games = new LinkedList<BoardGame>();
        BgtDataManager dbManager = getBgtDataManager();

        // create players
        for (int i = 0; i < numOfPlayers; i++) {
            Player newPlayer = dbManager.createNewPlayer(faker.name().fullName(), faker.pokemon().name());
            players.add(newPlayer);
        }

        // create 5 games
        {
            games.add(dbManager.createNewBoardgame("Eclipse: Second Dawn", "https://boardgamegeek.com/boardgame/246900/eclipse-second-dawn-galaxy"));
            games.add(dbManager.createNewBoardgame("Everdell", "https://boardgamegeek.com/boardgame/199792/everdell"));
            games.add(dbManager.createNewBoardgame("Wingspan", "https://boardgamegeek.com/boardgame/266192/wingspan"));
            games.add(dbManager.createNewBoardgame("Cthulhu Wars", "https://boardgamegeek.com/boardgame/139976/cthulhu-warsf"));
            games.add(dbManager.createNewBoardgame("Nemesis: Lockdown", "https://boardgamegeek.com/boardgame/310100/nemesis-lockdown"));
        }

        // create 5 play serssions
        for (int i = 0; i < numOfSessions; i++) {
            Collection<Player> sessionPlayers = rndSubset(players, 2 + RND.nextInt(4));
            PlaySession newSession = dbManager.createNewPlaySession(faker.date().past(365, TimeUnit.DAYS), rndSubset(sessionPlayers, 1).getFirst(), rndSubset(games, 1).getFirst(), 90 + RND.nextInt(90), sessionPlayers, rndSubset(sessionPlayers, 1).getFirst());
            sessions.add(newSession);
        }
        return sessions;
    }

}
