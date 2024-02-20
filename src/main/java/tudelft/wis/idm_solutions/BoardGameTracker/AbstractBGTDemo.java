package tudelft.wis.idm_solutions.BoardGameTracker;

import com.github.javafaker.Faker;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * An abstract implementation of a test class for the BGT. Can create random data
 * and has some other helper methods.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public abstract class AbstractBGTDemo {
    
    private static Faker faker = new Faker();
    private static final Random RND = new Random();

    /**
     * Returns a random subset of the specified collection of requested size.
     *
     * @param <T>   type parameter
     * @param c     collection to pick from
     * @param count requested size of the subset
     * @return list of randomly picked elements of the collection
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
     * @return BgtDataManager instance
     */
    public abstract BgtDataManager getBgtDataManager();

    /**
     * Creates several players and games, and adds them to the DB.
     *
     * @param numOfPlayers  the num of players
     * @param numOfSessions the num of sessions
     * @return collection of the play sessions
     * @throws BgtException the bgt exception
     */
    public Collection<PlaySession> createDummyData(int numOfPlayers, int numOfSessions) throws BgtException {
        Collection<PlaySession> sessions = new LinkedList<PlaySession>();
        Collection<Player> players = new LinkedList<Player>();
        Collection<BoardGame> games = new LinkedList<BoardGame>();
        BgtDataManager dbManager = getBgtDataManager();

        // Create 5 games
        {
            games.add(dbManager.createNewBoardgame("Eclipse: Second Dawn", "https://boardgamegeek.com/boardgame/246900/eclipse-second-dawn-galaxy"));
            games.add(dbManager.createNewBoardgame("Everdell", "https://boardgamegeek.com/boardgame/199792/everdell"));
            games.add(dbManager.createNewBoardgame("Wingspan", "https://boardgamegeek.com/boardgame/266192/wingspan"));
            games.add(dbManager.createNewBoardgame("Cthulhu Wars", "https://boardgamegeek.com/boardgame/139976/cthulhu-warsf"));
            games.add(dbManager.createNewBoardgame("Nemesis: Lockdown", "https://boardgamegeek.com/boardgame/310100/nemesis-lockdown"));
        }

        // Create players
        for (int i = 0; i < numOfPlayers; i++) {
            Player newPlayer = dbManager.createNewPlayer(faker.name().fullName(), faker.pokemon().name());
            Collection<BoardGame> playerGames = rndSubset(games, RND.nextInt(3));
            for (BoardGame game : playerGames) {
                newPlayer.getGameCollection().add(game);
            }
            // Those games in the gameCollection are added AFTER the player was created. 
            // We thus need to persist it again to reflect that change.
            dbManager.persistPlayer(newPlayer);
            
            players.add(newPlayer);
        }

        // Create 5 play sessions
        for (int i = 0; i < numOfSessions; i++) {
            Collection<Player> sessionPlayers = rndSubset(players, 2 + RND.nextInt(4));
            PlaySession newSession = dbManager.createNewPlaySession(faker.date().past(365, TimeUnit.DAYS), rndSubset(sessionPlayers, 1).getFirst(), rndSubset(games, 1).getFirst(), 90 + RND.nextInt(90), sessionPlayers, rndSubset(sessionPlayers, 1).getFirst());
            sessions.add(newSession);
        }
        return sessions;
    }
    
}
