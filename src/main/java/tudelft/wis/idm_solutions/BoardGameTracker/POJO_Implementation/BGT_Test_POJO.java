/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.POJO_Implementation;

import java.sql.Connection;
import java.util.Collection;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;

/**
 *
 * @author chris
 */
public class BGT_Test_POJO extends tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo {

    private BgtDataManager_POJO dataManager = new BgtDataManager_POJO();

    @Override
    public BgtDataManager getBgtDataManager() {
        return dataManager;
    }

    /**
     * Just runs the application with some simple queries.
     *
     * @param args
     */
    public static void main(String args[]) {

        try {
            // Esteblish connection to local duck DB
            BGT_Test_POJO bgtTest = new BGT_Test_POJO();
            Connection connection = bgtTest.getConnection();
            Logger.info("Connected DB: " + connection.getMetaData().getURL());
            //
            Collection<PlaySession> sessions = bgtTest.createDummyData(12, 6);

            for (PlaySession session : sessions) {
                Logger.info("Session Created: \n" + session.toVerboseString());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
