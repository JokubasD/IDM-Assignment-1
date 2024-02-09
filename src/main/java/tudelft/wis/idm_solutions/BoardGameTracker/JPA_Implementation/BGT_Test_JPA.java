/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tudelft.wis.idm_solutions.BoardGameTracker.JPA_Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import org.tinylog.Logger;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;

/**
 *
 * @author chris
 */
public class BGT_Test_JPA extends tudelft.wis.idm_solutions.BoardGameTracker.AbstractBGTDemo {

    private static Connection connection;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:duckdb:./DB/bggt.duckdb");

        };
        return connection;
    }

    @Override
    public BgtDataManager getBgtDataManager() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Just runs the application with some simple queries.
     *
     * @param args
     */
    public static void main(String args[]) {

        try {
            BGT_Test_JPA bgtTest = new BGT_Test_JPA();

            // Establish connection to local duck DB
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
