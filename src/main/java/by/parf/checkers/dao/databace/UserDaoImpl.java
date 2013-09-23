package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.User;
import by.parf.checkers.dao.ProfileDao;
import by.parf.checkers.dao.UserDao;
import by.parf.checkers.factory.ProfileFactory;
import by.parf.checkers.service.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 11.8.13
 * Time: 19.45
 */
public class UserDaoImpl extends AbstractDatabaseDAO implements UserDao{

    private static String SELECT_USER_BY_ID = "select.user.by.id";
    private static String SELECT_USER_PASS = "select.user.pass";
    private static String SELECT_USER_BY_EMAIL = "select.user.by.email";

    private Connection connection;
    private PreparedStatement psSelectUserById;
    private PreparedStatement psSelectPassword;
    private PreparedStatement psSelectUserByEmail;

    private ProfileDao profileDao;

    public UserDaoImpl() {
        try {

        connection = getConnection();
        initQuerys();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initQuerys() throws SQLException {

        Properties props = PropertyManager.getSqlProperties();

        psSelectUserById = connection.prepareStatement(props.getProperty(SELECT_USER_BY_ID));
        psSelectPassword = connection.prepareStatement(props.getProperty(SELECT_USER_PASS));
        psSelectUserByEmail = connection.prepareStatement(props.getProperty(SELECT_USER_BY_EMAIL));

        profileDao = ProfileFactory.getClassFromFactory();
    }

    private User pickUser(ResultSet resultSet) throws SQLException {

        String COL_USER_ID = "id";
        String COL_FIRST_NAME = "firstname";
        String COL_LAST_NAME = "lastname";
        String COL_PURSE = "purse";
        String CAL_ACTIVE = "active";
        String COL_USER_EMAIl = "email";
        String COL_PROFILE_ID = "profileId";

        User user = new User(resultSet.getLong(COL_USER_ID));

        user.setFirstName(resultSet.getString(COL_FIRST_NAME));
        user.setLastName(resultSet.getString(COL_LAST_NAME));
        user.setEmail(resultSet.getString(COL_USER_EMAIl));
        user.setPurse(resultSet.getString(COL_PURSE));
        user.setActive(resultSet.getBoolean(CAL_ACTIVE));
        user.setProfile(profileDao.getProfileById(resultSet.getLong(COL_PROFILE_ID)));

        return user;
    }

    @Override
    public boolean authenticate(User user, String password) {

        final int numUserId = 1;
        final int numPass = 2;
        ResultSet resultSet = null;

        try {

            psSelectPassword.setLong(numUserId, user.getId());
            psSelectPassword.setString(numPass, password);
            resultSet = psSelectPassword.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User getUserById(long id) {

        final int numId = 1;
        ResultSet resultSet = null;
        User user = null;


        try {

            psSelectUserById.setLong(numId, id);
            resultSet = psSelectUserById.executeQuery();
            if (resultSet.next()) {
                user = pickUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        final int numEmail = 1;
        ResultSet resultSet = null;
        User user = null;

        try {
            psSelectUserByEmail.setString(numEmail, email);
            resultSet = psSelectUserByEmail.executeQuery();
            if (resultSet.next()) {
                user = pickUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(resultSet);
        }

        return user;
    }

    @Override
    public User removeUserById(long id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void close() {

        profileDao.close();

        closeConnection(psSelectUserById);
        closeConnection(psSelectUserByEmail);
        closeConnection(connection);
    }
}
