package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "play_sessions")
public class PlaySessionJPA implements PlaySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private PlayerJPA host;

    @ManyToOne
    private BoardGameJPA game;

    @ManyToMany
    @JoinTable(
            name = "play_session_players",
            joinColumns = @JoinColumn(name = "play_session_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Collection<PlayerJPA> allPlayers;

    @ManyToOne
    private PlayerJPA winner;

    private int playtime;
    public PlaySessionJPA() {}
    public PlaySessionJPA(Date date, PlayerJPA host, BoardGameJPA game, Collection<PlayerJPA> allPlayers, PlayerJPA winner, int playtime) {
        this.date = date;
        this.host = host;
        this.game = game;
        this.allPlayers = allPlayers;
        this.winner = winner;
        this.playtime = playtime;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHost(PlayerJPA host) {
        this.host = host;
    }

    public void setGame(BoardGameJPA game) {
        this.game = game;
    }

    public void setAllPlayers(Collection<PlayerJPA> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public void setWinner(PlayerJPA winner) {
        this.winner = winner;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    @Override
    public Collection<Player> getAllPlayers() {
        List<Player> playerInterfaces = new ArrayList<>();
        for (PlayerJPA playerJPA : allPlayers) {
            playerInterfaces.add(playerJPA);
        }
        return playerInterfaces;
    }

    @Override
    public Player getWinner() {
        return winner;
    }

    @Override
    public int getPlaytime() {
        return playtime;
    }

    @Override
    public String toVerboseString() {
        return "PlaySessionJPA{" +
                "id=" + id +
                ", date=" + date +
                ", host=" + host +
                ", game=" + game +
                ", allPlayers=" + allPlayers +
                ", winner=" + winner +
                ", playtime=" + playtime +
                '}';
    }
}
