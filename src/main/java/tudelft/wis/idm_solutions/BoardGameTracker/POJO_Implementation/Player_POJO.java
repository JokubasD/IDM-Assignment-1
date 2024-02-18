/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.util.Collection;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 * POJO (Plain Old Java Object) Implementation without any database
 * functionality.
 *
 * @author chris
 */
public class Player_POJO implements Player {

    private String name;
    private String nickName;
    private Collection<BoardGame> gameCollection;

    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public String getPlayerNickName() {
        return nickName;
    }

    @Override
    public Collection<BoardGame> getGameCollection() {
        return gameCollection;
    }

    @Override
    public String toVerboseString() {
        String result = name;
        if (nickName != null) {
            result = result + " (" + nickName + ")";
        }
        return result;
    }

    public Player_POJO(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public void addGameToCollection(BoardGame game) {
        this.gameCollection.add(game);
    }

}
