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
 * @author Christoph Lofi, Alexandra Neagu
 */
public class BoardGame_POJO implements BoardGame {

    /**
     * The Name.
     */
    String name;
    /**
     * The BoardGameGeek.com URL of the boardgame.
     */
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

    /**
     * Instantiates a new Board game POJO.
     *
     * @param name   the name
     * @param bggURL the BoardGameGeek.com URL of the boardgame
     */
    public BoardGame_POJO(String name, String bggURL) {
        this.name = name;
        this.bggURL = bggURL;
    }

}
