package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;
import java.util.Date;

public class PlaySessionJDBC implements PlaySession {
    private Date date;
    private Player host;
    private BoardGame game;
    private int playtime;
    private Collection<Player> players;
    private Player winner;

    public PlaySessionJDBC(Date date, Player host, BoardGame game, int playtime, Collection<Player> players, Player winner) {
        this.date = date;
        this.host = host;
        this.game = game;
        this.playtime = playtime;
        this.players = players;
        this.winner = winner;
    }

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
    public int getPlaytime() {
        return playtime;
    }

    @Override
    public String toVerboseString() {
        return this.toString();
    }

    @Override
    public Player getWinner() {
        return winner;
    }
}