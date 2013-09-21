package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.Evaluation;
import by.parf.checkers.beans.Team;
import by.parf.checkers.dao.EvaluationDao;
import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.dao.UserDao;
import by.parf.checkers.factory.MatchFactory;
import by.parf.checkers.factory.UserFactory;
import by.parf.checkers.service.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 19.9.13
 * Time: 16.12
 */
public class EvaluationDaoImpl extends AbstractDatabaseDAO implements EvaluationDao {

    private final static String SELECT_EVALUATION_BY_MATCH_ID = "select.evaluation.by.match.id";
    private final static String INSERT_EVALUATION = "insert.evaluation";
    private final static String SELECT_MAX_EVALUATION_ID="select.max.evaluation.id";

    private Connection connection;
    private PreparedStatement psSelectEvaluationsByMatchId;
    private PreparedStatement psInsertEvaluation;
    private PreparedStatement psSelectMaxId;

    private MatchDao matchDao;
    private UserDao userDao;

    public EvaluationDaoImpl() {

        try {
            connection = getConnection();
            initQuerys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initQuerys() throws SQLException {


        Properties props = PropertyManager.getSqlProperties();
        psSelectEvaluationsByMatchId = connection.prepareStatement(props.getProperty(SELECT_EVALUATION_BY_MATCH_ID));
        psInsertEvaluation = connection.prepareStatement(props.getProperty(INSERT_EVALUATION));
        psSelectMaxId = connection.prepareStatement(props.getProperty(SELECT_MAX_EVALUATION_ID));
        matchDao = MatchFactory.getClassFromFactory();
        userDao = UserFactory.getClassFromFactory();

    }

    private Evaluation pickEvaluation(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_USER_ID = "userId";
        String COL_MATCH_ID = "matcheId";
        String COL_GOALS1 = "goals1";
        String COL_GOALS2 = "goals2";

        Evaluation evaluation = new Evaluation(resultSet.getLong(COL_ID));
        evaluation.setMatch(matchDao.getMatchById(resultSet.getLong(COL_MATCH_ID)));
        evaluation.setUser(userDao.getUserById(resultSet.getLong(COL_USER_ID)));
        evaluation.setTeamFirstGoals(resultSet.getInt(COL_GOALS1));
        evaluation.setTeamSecondGoals(resultSet.getInt(COL_GOALS2));

        return evaluation;
    }

    @Override
    public Evaluation getEvaluationById(long id) {
        return null;
    }

    @Override
    public List<Evaluation> getEvaluationListByMatchId(long matchId) {

        int numMatchId = 1;
        ResultSet resultSet = null;
        List<Evaluation> evaluationList = new ArrayList<Evaluation>();

        try {
            psSelectEvaluationsByMatchId.setLong(numMatchId, matchId);
            resultSet = psSelectEvaluationsByMatchId.executeQuery();

            while (resultSet.next()) {
                evaluationList.add(pickEvaluation(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evaluationList;
    }

    @Override
    public long getMaxId() {

        String COL_MAX_ID = "max";
        ResultSet resultSet = null;

        try {

            resultSet = psSelectMaxId.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(COL_MAX_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {

        int numId = 1;
        int numUserId = 2;
        int numMatcheId = 3;
        int numGoals1 = 4;
        int numGoals2 = 5;

        try {
            psInsertEvaluation.setLong(numId, evaluation.getId());
            psInsertEvaluation.setLong(numUserId, evaluation.getUser().getId());
            psInsertEvaluation.setLong(numMatcheId, evaluation.getMatch().getId());
            psInsertEvaluation.setInt(numGoals1, evaluation.getTeamFirstGoals());
            psInsertEvaluation.setInt(numGoals2, evaluation.getTeamSecondGoals());
            psInsertEvaluation.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        closeConnection(psSelectEvaluationsByMatchId);
        closeConnection(connection);
        matchDao.close();
        userDao.close();
    }
}