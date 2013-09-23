package by.parf.checkers.factory;

import by.parf.checkers.dao.ProfileDao;
import by.parf.checkers.dao.databace.ProfileDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 23.9.13
 * Time: 22.28
 */
public class ProfileFactory {

    private ProfileFactory() {
        // Prevent instantiation
    }

    public static ProfileDao getClassFromFactory() {
        return new ProfileDaoImpl();
    }

}
