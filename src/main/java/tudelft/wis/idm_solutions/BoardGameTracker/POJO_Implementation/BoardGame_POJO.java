/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

/**
 * POJO (Plain Old Java Object) Implementation without any database
 * functionality.
 *
 * @author chris
 */
public class BoardGame_POJO implements BoardGame {

    String name;
    String bggURL;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBGG_URL() {
        return bggURL;
    }

    @Override
    public String toVerboseString() {
        return name + " (" + bggURL + ")";
    }

    public BoardGame_POJO(String name, String bggURL) {
        this.name = name;
        this.bggURL = bggURL;
    }

}
