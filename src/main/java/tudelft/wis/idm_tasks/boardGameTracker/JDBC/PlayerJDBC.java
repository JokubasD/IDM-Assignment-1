package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerJDBC implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player {
    String name;
    String nickname;
    Collection<BoardGame> boardGames ;

    public PlayerJDBC(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        this.boardGames = new ArrayList<>();
    }
    public PlayerJDBC(String name, String nickname, Collection<BoardGame> games) {
        this.name = name;
        this.nickname = nickname;
        this.boardGames = games;
    }


    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerNickName() {
        return nickname;
    }


    @Override
    public Collection<BoardGame> getGameCollection() {
        if (boardGames == null) {
            boardGames = new ArrayList<>();
        }
        return boardGames.isEmpty() ? new ArrayList<>() : boardGames;
    }

    @Override
    public String toVerboseString() {
        String boardGameString = boardGames == null ? "no board games" : "";
        if (boardGames != null) {
            for (BoardGame game : boardGames) {
                boardGameString += game.toVerboseString() + "\n";
            }
        }
        return "Player " + name + ", " +
                "with nickname " + nickname + ", "
                + boardGameString;
    }

    @Override
    public String toString() {
        return toVerboseString();
    }
}
