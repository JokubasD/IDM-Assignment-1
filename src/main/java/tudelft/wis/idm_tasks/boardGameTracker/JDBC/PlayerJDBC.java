package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import java.util.Collection;

public class PlayerJDBC implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player {
    String name;
    String nickname;

    public PlayerJDBC(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
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
        return null;
    }

    @Override
    public String toVerboseString() {
        return "This is the players name: " + name + "\nThis is the players nickname: " + nickname;
    }

    @Override
    public String toString() {
        return toVerboseString();
    }
}
