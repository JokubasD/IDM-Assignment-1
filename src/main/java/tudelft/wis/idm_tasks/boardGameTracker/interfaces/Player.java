package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.util.Collection;

/**
 * A player typically joining game night.
 * 
 * @author chris
 */
public interface Player {
    
    /**
     * 
     * @return the name of the player 
     */
    public String getPlayerName();
    
    
    /**
     * 
     * @return the nickName of the player 
     */
    public String getPlayerNickName();
    
    
    /**
     * Returns the Collection of board games this player owns (if any).
     * @return the collection
     */
    public Collection<BoardGame> getGameCollection(); 
    
    /**
     * Adds a new game to the collection of a player
     * @param game 
     */
    public void addGameToCollection(BoardGame game);
    
    
    /**
     * Creates a human-readable string representation of this object
     * @return 
     */
    public String toVerboseString();
    
}
