package by.parf.checkers.factory;

import by.parf.checkers.dao.EvaluationDao;
import by.parf.checkers.dao.databace.EvaluationDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 19.9.13
 * Time: 16.58
 */
public class EvaluationFactory {

    private EvaluationFactory() {
        // Prevent instantiation
    }

    public static EvaluationDao getClassFromFactory() {
        return new EvaluationDaoImpl();
    }
}
