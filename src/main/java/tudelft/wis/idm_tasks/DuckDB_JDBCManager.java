package tudelft.wis.idm_tasks;

import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This simple JDBC manager returns a (shared) connection to a local file system embedded DuckDB instance.
 * This is a somewhat lazy implementation which just uses a static field for pooling...
 *
 * @author Christoph Lofi, Alexandra Neagu
 */
public class DuckDB_JDBCManager implements JDBCManager {

    private static Connection connection;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            Connection conn = DriverManager.getConnection("jdbc:duckdb:./DB/bggt.duckdb");

        };
        return connection;
    }
    
    
}
