package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.util.Collection;
import java.util.Date;

/**
 * A play session in which one single game is played.
 *
 * @author chris
 */
public interface PlaySession {

    /**
     *
     * @return The date of the play session
     */
    public Date getDate();

    /**
     *
     * @return the player who hosted / organized this game session.
     */
    public Player getHost();

    /**
     *
     * @return The game which was played.
     */
    public BoardGame getGame();

    /**
     *
     * @return All players who joined that session
     */
    public Collection<Player> getAllPlayers();

    /**
     * Returns the winner of the game. This is somewhat naively assuming that
     * thee is only one player, but yea, simplicity. Can be NULL if nobody won.
     *
     * @return the winner
     */
    public Player getWinner();

    /**
     *
     * @return returns the approximate playtime in minutes for this session
     */
    public int getPlaytime();

    /**
     * Creates a human-readable string representation of this object
     *
     * @return
     */
    public String toVerboseString();

}
