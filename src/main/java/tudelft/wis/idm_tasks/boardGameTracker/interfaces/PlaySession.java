package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.util.Collection;
import java.util.Date;

// Add other necessary imports here

/**
 * A play session in which one single game is played.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public interface PlaySession {

    /**
     * Returns the date of the play session.
     *
     * @return date of the play session
     */
    public Date getDate();
        // @TODO: Implement this method.

    /**
     * Returns the player who hosted or organized this game session.
     *
     * @return player who hosted/organized this game session
     */
    public Player getHost();
        // @TODO: Implement this method.

    /**
     * Returns the game which was played.
     *
     * @return game which was played
     */
    public BoardGame getGame();
        // @TODO: Implement this method.

    /**
     * Returns all the players who joined the session.
     *
     * @return collection of players who joined the session
     */
    public Collection<Player> getAllPlayers();
        // @TODO: Implement this method.

    /**
     * Returns the winner of the game. This is somewhat naively assuming that
     * thee is only one player, but yeah, simplicity. Can be Null if nobody won.
     *
     * @return the player who is the winner, or Null if there is no winner
     */
    public Player getWinner();
        // @TODO: Implement this method.

    /**
     * Returns the approximate playtime, in minutes, for the session.
     *
     * @return an integer representing the approximate playtime in minutes for this session
     */
    public int getPlaytime();
        // @TODO: Implement this method.

    /**
     * Creates a human-readable String representation of this object.
     *
     * @return the string representation of the object
     */
    public String toVerboseString();
        // @TODO: Implement this method.

}
