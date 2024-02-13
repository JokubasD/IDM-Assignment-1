/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import com.github.javafaker.GameOfThrones;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 * Implements an in-memory POJO database using Linked Lists. This is sooooo
 * insanely bad, it could even be for Osiris...
 *
 * @author chris
 */
public class BgtDataManager_POJO implements BgtDataManager {

    private List<Player_POJO> players = new LinkedList<Player_POJO>();
    private List<BoardGame_POJO> games = new LinkedList<BoardGame_POJO>();
    private List<PlaySession_POJO> sessions = new LinkedList<PlaySession_POJO>();

    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        Player_POJO player = new Player_POJO(name, nickname);
        players.add(player);
        return player;
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        BoardGame_POJO game = new BoardGame_POJO(name, bggURL);
        games.add(game);
        return game;
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) throws BgtException {
        PlaySession_POJO session = new PlaySession_POJO(date, host, game, playtime, players, winner);
        sessions.add(session);
        return session;
    }

    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
