package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCManager {

    /**
     *
     * @return Returns a JDBC connection to a database.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException;

}
