/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tudelft.wis.idm_tasks.boardGameTracker.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author chris
 */
public interface BgtDataManager {

    /**
     * Creates a new player and stores it in the DB.
     *
     * @param name player name
     * @param nickname player nickname
     * @return the new player 
     * @throws java.sql.SQLException DB trouble
     */
    public Player createNewPlayer(String name, String nickname) throws SQLException;

    /**
     * Creates a new board game and stores it in the DB.
     * @param name  name of the game
     * @param bggURL the URL of the game at boardgamesGeek.com
     * @return the new game
     * @throws SQLException DB trouble
     */
    public BoardGame createNewBoardgame(String name, String bggURL) throws SQLException;

    
    /**
     * Creates a new play session and stores it in the DB.
     * @param date date of the session
     * @param host the session host
     * @param game the game which was played
     * @param playtime approximate playtime in minutes 
     * @param players all players
     * @param winner the one player who won (NULL in case of no winner; multiple winners not supported)
     * @return the new play session
     * @throws SQLException DB trouble
     */
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws SQLException;

}
