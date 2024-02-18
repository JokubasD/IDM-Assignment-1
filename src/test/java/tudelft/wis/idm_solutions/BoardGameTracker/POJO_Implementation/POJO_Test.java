/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

/**
 *
 * @author chris
 */
public class POJO_Test extends tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo {

    public POJO_Test() {
    }

    private BgtDataManager_POJO dataManager = new BgtDataManager_POJO();

    @Override
    public BgtDataManager getBgtDataManager() {
        return dataManager;
    }

    /**
     * The POJO implementation does not use databases; thus no Connection
     * needed.
     *
     * @return nothing
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    /**
     * Just runs the application with some simple queries.
     *
     * @param args
     */
    @Test
    public void basicTest() {

        try {

            // create dummy data
            Collection<PlaySession> testSessions = this.createDummyData(12, 6);

            for (PlaySession session : testSessions) {
                Logger.info("Session Created: \n" + session.toVerboseString());
            }

            // get dummy session & related data
            PlaySession firstsession = testSessions.iterator().next();
            Player host = firstsession.getHost();
            BoardGame game = firstsession.getGame();

            // retrieve the host from the database
            Player retrievedPlayer = this.getBgtDataManager().findPlayersByName(host.getPlayerName()).iterator().next();
            assertEquals(retrievedPlayer.getPlayerName(), retrievedPlayer.getPlayerNickName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
