package by.parf.checkers.factory;

import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.dao.databace.MatchDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 31.8.13
 * Time: 19.52
 */
public class MatchFactory {

    private MatchFactory() {
        // Prevent instantiation
    }

    public static MatchDao getClassFromFactory() {
        return new MatchDaoImpl();
    }
}
