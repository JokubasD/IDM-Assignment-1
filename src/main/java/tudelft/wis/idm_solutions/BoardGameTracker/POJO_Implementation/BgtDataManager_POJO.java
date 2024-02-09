/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 *
 * @author chris
 */
public class BgtDataManager_POJO implements BgtDataManager {

    @Override
    public Player createNewPlayer(String name, String nickname) throws SQLException {
        return new Player_POJO(name, nickname);
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws SQLException {
        return new BoardGame_POJO(name, bggURL);
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws SQLException {
        return new PlaySession_POJO(date, host, game, playtime, players, winner);
    }

}
