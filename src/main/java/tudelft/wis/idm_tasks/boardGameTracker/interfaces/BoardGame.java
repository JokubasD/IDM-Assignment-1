package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

/**
 * A simplistic interface representing a board game.
 *
 * @author chris
 */
public interface BoardGame {

    /**
     * Returns the game name.
     *
     * @return game name
     */
    public String getName();

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    public String getBGG_URL();

    /**
     * Creates a human-readable string representation of this object
     *
     * @return
     */
    public String toVerboseString();

}
