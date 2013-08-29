package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.Match;
import by.parf.checkers.dao.MatchDao;
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
 * Date: 30.8.13
 * Time: 0.47
 */
public class MatchDaoImpl extends AbstractDatabaseDAO implements MatchDao {

    private static String SELECT_MATCHES = "select.matches";
    private static String SELECT_MATCH_BY_ID =  "select.match.by.id";
    private static String DELETE_MATCH_BY_ID =  "delete.match.by.id";
    private static String INSERT_MATCh = "insert.match";
    private static String SELECT_MAX_ID = "select.max.id";

    private Connection connection;
    private PreparedStatement psSelectMatches;
    private PreparedStatement psSelectMatchById;
    private PreparedStatement psDeleteMatchById;
    private PreparedStatement psInsertMatch;
    private PreparedStatement psSelectMaxId;

    public MatchDaoImpl() {

        try {

            connection = getConnection();
            initQuerys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initQuerys() throws SQLException {

        Properties props = PropertyManager.getSqlProperties();

        psSelectMatches = connection.prepareStatement(props.getProperty(SELECT_MATCHES));
        psSelectMatchById = connection.prepareStatement(props.getProperty(SELECT_MATCH_BY_ID));
        psDeleteMatchById = connection.prepareStatement(props.getProperty(DELETE_MATCH_BY_ID));
        psInsertMatch = connection.prepareStatement(props.getProperty(INSERT_MATCh));
        psSelectMaxId = connection.prepareStatement(props.getProperty(SELECT_MAX_ID));

    }

    private Match pickMatch(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_NAME = "name";
        String COL_TEAM1_ID = "team1id";
        String COL_TEAM2_Id = "team2id";
        String COL_TEAM1_GOALS = "team1goals";
        String COL_TEAM2_GOALS = "team2goals";
        String COL_DATE = "date";
        String COL_TIME = "time";

        Match match = new Match(resultSet.getLong(COL_ID));
        match.setName(resultSet.getString(COL_NAME));
        //match.setTeamFirst(resultSet.get);
        //match.setTeamSecond();
        match.setTeamFirstGoals(resultSet.getInt(COL_TEAM1_GOALS));
        match.setTeamSecondGoals(resultSet.getInt(COL_TEAM2_GOALS));
        match.setTime(resultSet.getTime(COL_TIME));
        match.setDate(resultSet.getDate(COL_DATE));

        return match;
    }

    @Override
    public Match getMatchById(long id) {

        int numId = 1;
        ResultSet resultSet = null;

        try {

            psSelectMatchById.setLong(numId, id);
            resultSet =  psSelectMatchById.executeQuery();

            if (resultSet.next()) {
                return pickMatch(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Match> getMatchList() {

        ResultSet resultSet = null;
        List<Match> matchList = new ArrayList<Match>();

        try {
            resultSet = psSelectMatches.executeQuery();

            while (resultSet.next()) {
                matchList.add(pickMatch(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchList;
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
    public void removeMatch(Match match) {

        int numId = 1;
        ResultSet resultSet = null;

        try {

            psDeleteMatchById.setLong(numId, match.getId());
            psDeleteMatchById.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateMatch(Match match) {

    }

    @Override
    public void addMatch(Match match) {

        int numId = 1;
        int numName = 2;
        int numTeam1Id = 3;
        int numTeam2Id = 4;
        int numTeam1Goals = 5;
        int numTeam2Goals = 6;
        int numDate = 7;
        int numTime = 8;

        try {

            psInsertMatch.setLong(numId, match.getId());
            psInsertMatch.setString(numName, match.getName());
            psInsertMatch.setLong(numTeam1Id, 0);
            psInsertMatch.setLong(numTeam2Id, 1);
            psInsertMatch.setInt(numTeam1Goals, match.getTeamFirstGoals());
            psInsertMatch.setInt(numTeam2Goals, match.getTeamSecondGoals());
            psInsertMatch.setDate(numDate, match.getDate());
            psInsertMatch.setTime(numTime, match.getTime());
            psInsertMatch.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        closeConnection(psSelectMatches);
        closeConnection(psSelectMatchById);
        closeConnection(psDeleteMatchById);
        closeConnection(psInsertMatch);
        closeConnection(psSelectMaxId);
        closeConnection(connection);
    }
}
