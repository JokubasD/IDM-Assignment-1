/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.util.Collection;
import java.util.Date;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 * POJO (Plain Old Java Object) Implementation without any database
 * functionality.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class PlaySession_POJO implements PlaySession {

    private Date date;
    private Player host;
    private BoardGame game;
    private int playTime;
    private Collection<Player> players;
    private Player winner;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Player getHost() {
        return host;
    }

    @Override
    public BoardGame getGame() {
        return game;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        return players;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public int getPlaytime() {
        return playTime;
    }

    @Override
    public String toVerboseString() {
        String result = game.toVerboseString() + " {";
        result = result + "\n  Date: " + date.toString();
        result = result + "\n  Playtime: " + playTime;
        result = result + "\n  Host: " + host.toVerboseString();
        result = result + "\n  Players: ";
        for (Player player : players) {
            result = result + player.toVerboseString() + "; ";
        }
        result = result + "\n}\n";
        return result;
    }

    /**
     * Instantiates a new Play session POJO.
     *
     * @param date     the date
     * @param host     the host
     * @param game     the game
     * @param playTime the play time
     * @param players  the players
     * @param winner   the winner
     */
    public PlaySession_POJO(Date date, Player host, BoardGame game, int playTime, Collection<Player> players, Player winner) {
        this.date = date;
        this.host = host;
        this.game = game;
        this.playTime = playTime;
        this.players = players;
        this.winner = winner;
    }

}
