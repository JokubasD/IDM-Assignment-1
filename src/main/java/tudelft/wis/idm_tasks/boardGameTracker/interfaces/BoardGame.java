package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

// Add other necessary imports here

/**
 * A simplistic interface representing a board game.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public interface BoardGame {

    /**
     * Returns the game name.
     *
     * @return game name
     */
    public String getName();
        // @TODO: Implement this method.

    /**
     * Returns the game's BoardGamesGeek.com URL.
     *
     * @return the URL as a string
     */
    public String getBGG_URL();
        // @TODO: Implement this method.

    /**
     * Creates a human-readable String representation of this object.
     *
     * @return the string representation of the object
     */
    public String toVerboseString();
        // @TODO: Implement this method.

}
