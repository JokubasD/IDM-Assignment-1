package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "players")
@Entity
public class PlayerJPA implements Player {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String nickname;

    @JoinColumn(name = "player_id")
    @OneToMany
    private Collection<BoardGameJPA> gameCollection;


    public PlayerJPA() {
    }

    public PlayerJPA(Long id, String name, String nickname, Collection<BoardGameJPA> gameCollection) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.gameCollection = gameCollection;
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public String getPlayerNickName() {
        return null;
    }

    public Collection<BoardGame> getGameCollection() {
        List<BoardGame> boardInterface = new ArrayList<>();
        for (BoardGameJPA boardGameJPA : gameCollection) {
            boardInterface.add(boardGameJPA);
        }
        return boardInterface;
    }

    @Override
    public String toVerboseString() {
        String boardGameString = gameCollection == null ? "no board games" : "";
        if (gameCollection != null) {
            for (BoardGame game : gameCollection) {
                boardGameString += game.toVerboseString() + "\n";
            }
        }
        return "Player " + name + ", " +
                "with nickname " + nickname + ", "
                + boardGameString;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGameCollection(Collection<BoardGameJPA> gameCollection) {
        this.gameCollection = gameCollection;
    }
}
