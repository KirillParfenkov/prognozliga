package by.parf.checkers.factory;

import by.parf.checkers.dao.TeamDao;
import by.parf.checkers.dao.databace.TeamDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 31.8.13
 * Time: 19.50
 */
public class TeamFactory {

    private TeamFactory() {
        // Prevent instantiation
    }

    public static TeamDao getClassFromFactory() {
        return new TeamDaoImpl();
    }

}
