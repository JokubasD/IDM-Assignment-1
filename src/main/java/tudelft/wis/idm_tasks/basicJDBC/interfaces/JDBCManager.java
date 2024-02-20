package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

// Add other necessary imports here

/**
 * The interface JDBC Manager.
 */
public interface JDBCManager {

    /**
     * Gets connection.
     *
     * @return Returns a JDBC connection to a database.
     * @throws SQLException           the SQL exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException;
        // @TODO: Implement this method.

}
