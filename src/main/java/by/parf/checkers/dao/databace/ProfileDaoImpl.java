package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.Profile;
import by.parf.checkers.dao.ProfileDao;
import by.parf.checkers.service.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 23.9.13
 * Time: 22.16
 */
public class ProfileDaoImpl extends AbstractDatabaseDAO implements ProfileDao {

    private static String SELECT_PROFILE_BY_ID = "select.profile.by.id";


    private Connection connection;
    private PreparedStatement psSelectProfileById;


    public ProfileDaoImpl() {
        try {

            connection = getConnection();
            initQuerys();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Profile pickProfile(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_NAME = "name";

        Profile profile = new Profile(resultSet.getLong(COL_ID));
        resultSet.getString(COL_NAME);

        return profile;
    }

    private void initQuerys() throws SQLException {

        Properties props = PropertyManager.getSqlProperties();

        psSelectProfileById = connection.prepareStatement(props.getProperty(SELECT_PROFILE_BY_ID));

    }


    @Override
    public Profile getProfileById(long profileId) {

        int numId = 1;
        ResultSet resultSet = null;

        try {

            psSelectProfileById.setLong(numId, profileId);
            resultSet = psSelectProfileById.executeQuery();

            if (resultSet.next()) {
                return pickProfile(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {

        closeConnection(psSelectProfileById);
        closeConnection(connection);
    }
}
