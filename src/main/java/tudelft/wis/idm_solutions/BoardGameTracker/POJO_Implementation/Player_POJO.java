/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.util.Collection;
import java.util.LinkedList;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 * POJO (Plain Old Java Object) Implementation without any database
 * functionality.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class Player_POJO implements Player {

    private String name;
    private String nickName;
    private Collection<BoardGame> gameCollection = new LinkedList<BoardGame>();

    /**
     * Instantiates a new Player POJO.
     *
     * @param name     name
     * @param nickName nickname
     */
    public Player_POJO(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

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

}
