package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.util.Collection;

// Add other necessary imports here

/**
 * A player typically joining a game night.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public interface Player {
    
    /**
     * Returns the name of the player.
     *
     * @return name of the player
     */
    public String getPlayerName();
        // @TODO: Implement this method.
    
    
    /**
     * Returns the nickname of the player.
     * 
     * @return nickname of the player
     */
    public String getPlayerNickName();
        // @TODO: Implement this method.
    
    
    /**
     * Returns all the boardgames this player owns (if any).
     * @return collection of boardgames this player owns
     */
    public Collection<BoardGame> getGameCollection();
        // @TODO: Implement this method.
   
    
    /**
     * Creates a human-readable String representation of this object.
     *
     * @return the string representation of the object
     */
    public String toVerboseString();
        // @TODO: Implement this method.
    
}
