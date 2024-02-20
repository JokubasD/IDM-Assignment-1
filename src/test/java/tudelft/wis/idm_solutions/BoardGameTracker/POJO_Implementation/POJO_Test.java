/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import org.junit.jupiter.api.Test;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type POJO test.
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class POJO_Test extends tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo {

    /**
     * Instantiates a new POJO test.
     */
    public POJO_Test() {
    }

    private BgtDataManager_POJO dataManager = new BgtDataManager_POJO();

    @Override
    public BgtDataManager getBgtDataManager() {
        return dataManager;
    }

    /**
     * Just runs the application with some simple queries and assertions. It's
     * not very comprehensive, essentially, just a single session is retrieved
     * and the hist and the game is being checked.
     */
    @Test
    public void basicTest() throws BgtException {

        // Make sure to start this test with an empty DB - trivial for POJO though...
        // Create dummy data
        Collection<PlaySession> testSessions = this.createDummyData(12, 6);

        for (PlaySession session : testSessions) {
            Logger.info("Session Created: \n" + session.toVerboseString());
        }

        // Get dummy session & related data
        PlaySession firstsession = testSessions.iterator().next();
        Player host = firstsession.getHost();
        BoardGame game = firstsession.getGame();

        // Retrieve the host from the database and check if it returns correctly
        Player retrievedPlayer = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(retrievedPlayer.getPlayerNickName(), retrievedPlayer.getPlayerNickName());
        assertEquals(retrievedPlayer.getGameCollection().size(), host.getGameCollection().size());
        Logger.info("Player check passed: " + retrievedPlayer.getPlayerName() + "; collectionSize: " + retrievedPlayer.getGameCollection().size());

        // Retrieve the game from the database and check if it returns correctly
        BoardGame retrievedGame = this.getBgtDataManager().findGamesByName(game.getName()).iterator().next();
        assertEquals(retrievedGame.getBGG_URL(), game.getBGG_URL());

        // Retrieve session by date
        Collection<PlaySession> retrievedSession = this.getBgtDataManager().findSessionByDate(firstsession.getDate());
        assertEquals(firstsession.getDate(), retrievedSession.iterator().next().getDate());

        // Remove a game from the host's collection, add  it again
        BoardGame firstGame = host.getGameCollection().iterator().next();
        int numOfGames = host.getGameCollection().size();
        host.getGameCollection().remove(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        Player hostFromDB = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames - 1, hostFromDB.getGameCollection().size());

        // Add the game again
        hostFromDB.getGameCollection().add(firstGame);
        this.getBgtDataManager().persistPlayer(host);

        // Load the host again from DB
        Player hostFromDB2 = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
        assertEquals(numOfGames, hostFromDB2.getGameCollection().size());

    }

}
