package by.parf.checkers.factory;

import by.parf.checkers.dao.UserDao;
import by.parf.checkers.dao.databace.UserDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 18.8.13
 * Time: 0.11
 */
public final class UserDAOFactory {

    private UserDAOFactory() {
        // Prevent instantiation
    }

    public static UserDao getClassFromFactory() {
        return new UserDaoImpl();
    }

}
