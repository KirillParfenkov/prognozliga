package by.parf.checkers.dao.databace;

import by.parf.checkers.service.PropertyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 11.8.13
 * Time: 19.51
 */


public abstract class AbstractDatabaseDAO {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String ERROR_MESSAGE = "Can't close connection.";

    private static final String PATH_DB = "db.path";
    private static final String DB_ADMIN_NAME = "db.admin.name";
    private static final String DB_ADMIN_PASS = "db.admin.pass";

    /**
     * @return Connection
     */
    protected Connection getConnection() {

        Properties dbProps = PropertyManager.getDatabaseProperties();

        Connection connection;
        try {

            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbProps.getProperty(PATH_DB),
                                                     dbProps.getProperty(DB_ADMIN_NAME),
                                                     dbProps.getProperty(DB_ADMIN_PASS));
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param statement closing statement
     */
    protected void closeConnection(PreparedStatement statement) {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println(ERROR_MESSAGE + e);
        }
    }

    /**
     *
     * @param statement closing statement
     */
    protected void closeConnection(Statement statement) {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println(ERROR_MESSAGE + e);
        }
    }

    /**
     *
     * @param connection closing connection
     */
    protected void closeConnection(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(ERROR_MESSAGE + e);
        }
    }

    /**
     *
     * @param resultSet closing result set
     */
    protected void closeConnection(ResultSet resultSet) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println(ERROR_MESSAGE + e);
        }
    }
}
