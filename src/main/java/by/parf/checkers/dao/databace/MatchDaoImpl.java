package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.Match;
import by.parf.checkers.beans.MatchSet;
import by.parf.checkers.dao.EvaluationDao;
import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.dao.TeamDao;
import by.parf.checkers.factory.EvaluationFactory;
import by.parf.checkers.factory.TeamFactory;
import by.parf.checkers.service.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private static String SELECT_MAX_ID = "select.max.match.id";
    private static String SELECT_LIMITED_NUM_MATCHES_FROM_ID = "select.limited.num.matches.from.id";
    private static String SELECT_LIMITED_NUM_MATCH_SETS_FROM_ID = "select.limited.num.matchSets.from.id";
    private static String INSERT_MATCH_SET = "insert.matchSet";
    private static String SELECT_MAX_MATCH_SET_ID = "select.max.matchSet.id";
    private static String SELECT_MATCH_SET_BY_ID = "select.matchSet.by.id";
    private static String INSERT_MATCH_TO_MATCH_SET = "insert.match.to.match.set";
    private static String SELECT_MATCHES_BY_MATCH_SET_Id = "select.matches.by.matchSet.id";
    private static String SELECT_MATCH_SET_BY_DATE = "select.matchSet.by.date";

    private Connection connection;
    private PreparedStatement psSelectMatches;
    private PreparedStatement psSelectMatchById;
    private PreparedStatement psDeleteMatchById;
    private PreparedStatement psInsertMatch;
    private PreparedStatement psSelectMaxId;
    private PreparedStatement psSelectLimitedNumMatchesFromId;
    private PreparedStatement psSelectLimitedNumMatchSetsFromId;
    private PreparedStatement psInsertMatchSet;
    private PreparedStatement psSelectMaxMatchSetId;
    private PreparedStatement psSelectMatchSetById;
    private PreparedStatement psInsertMatchToMatchSet;
    private PreparedStatement psSelectMatchesByMatchSetId;
    private PreparedStatement psSelectMatchSetByDate;

    private TeamDao teamDao;
    //private EvaluationDao evaluationDao;

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
        psSelectLimitedNumMatchesFromId = connection.prepareStatement(props.getProperty(SELECT_LIMITED_NUM_MATCHES_FROM_ID));
        psSelectLimitedNumMatchSetsFromId = connection.prepareStatement(props.getProperty(SELECT_LIMITED_NUM_MATCH_SETS_FROM_ID));
        psInsertMatchSet = connection.prepareStatement(props.getProperty(INSERT_MATCH_SET));
        psSelectMaxMatchSetId = connection.prepareStatement(props.getProperty(SELECT_MAX_MATCH_SET_ID));
        psSelectMatchSetById = connection.prepareStatement(props.getProperty(SELECT_MATCH_SET_BY_ID));
        psInsertMatchToMatchSet = connection.prepareStatement(props.getProperty(INSERT_MATCH_TO_MATCH_SET));
        psSelectMatchesByMatchSetId = connection.prepareStatement(props.getProperty(SELECT_MATCHES_BY_MATCH_SET_Id));
        psSelectMatchSetByDate = connection.prepareStatement(props.getProperty(SELECT_MATCH_SET_BY_DATE));

        teamDao = TeamFactory.getClassFromFactory();
        //evaluationDao = EvaluationFactory.getClassFromFactory();

    }

    private Match pickMatch(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_NAME = "name";
        String COL_TEAM1_ID = "team1id";
        String COL_TEAM2_ID = "team2id";
        String COL_TEAM1_GOALS = "team1goals";
        String COL_TEAM2_GOALS = "team2goals";
        String COL_DATE = "date";
        String COL_TIME = "time";

        Match match = new Match(resultSet.getLong(COL_ID));
        match.setName(resultSet.getString(COL_NAME));
        match.setTeamFirst(teamDao.getTeamById(resultSet.getLong(COL_TEAM1_ID)));
        match.setTeamSecond(teamDao.getTeamById(resultSet.getLong(COL_TEAM2_ID)));
        match.setTeamFirstGoals(resultSet.getInt(COL_TEAM1_GOALS));
        match.setTeamSecondGoals(resultSet.getInt(COL_TEAM2_GOALS));
        match.setTime(resultSet.getTime(COL_TIME));
        match.setDate(resultSet.getDate(COL_DATE));

        return match;
    }

    @Override
    public List<Match> getLimitedMatchListFromId(long fromId, long number) {

        int numFromId = 1;
        int numNumber = 2;

        List<Match> matchList = new ArrayList<Match>();
        ResultSet resultSet = null;

        try {
            psSelectLimitedNumMatchesFromId.setLong(numFromId, fromId);
            psSelectLimitedNumMatchesFromId.setLong(numNumber, number);
            resultSet = psSelectLimitedNumMatchesFromId.executeQuery();

            while (resultSet.next()) {
                matchList.add(pickMatch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchList;
    }

    @Override
    public List<MatchSet> getLimitedMatchSetListFromId(long fromId, long number) {

        int numFromId = 1;
        int numNumber = 2;

        List<MatchSet> matchSetList = new ArrayList<MatchSet>();
        ResultSet resultSet = null;

        try {
            psSelectLimitedNumMatchSetsFromId.setLong(numFromId, fromId);
            psSelectLimitedNumMatchSetsFromId.setLong(numNumber, number);
            resultSet = psSelectLimitedNumMatchSetsFromId.executeQuery();

            while (resultSet.next()) {
                matchSetList.add(pickMatchSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchSetList;
    }

    @Override
    public void addMatchSet(MatchSet matchSet) {

        int numId = 1;
        int numDate = 2;
        int numTitle = 3;

        int numMatchSetId = 1;
        int numMatchId = 2;

        List<Match> matchList = null;

        try {

            psInsertMatchSet.setLong(numId, matchSet.getId());
            psInsertMatchSet.setDate(numDate, new java.sql.Date(matchSet.getDate().getTime()));
            psInsertMatchSet.setString(numTitle, matchSet.getTitle());
            psInsertMatchSet.executeUpdate();


            psInsertMatchToMatchSet.setLong(numMatchSetId, matchSet.getId());

            for (Match match: matchSet.getMatches()) {
                psInsertMatchToMatchSet.setLong(numMatchId, match.getId());
                psInsertMatchToMatchSet.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Match> getMatchesByMatchSetId(long id){

        int numId = 1;
        List<Match> matchList = new ArrayList<Match>();
        ResultSet resultSet = null;

        try {
            psSelectMatchesByMatchSetId.setLong(numId, id);
            resultSet = psSelectMatchesByMatchSetId.executeQuery();
            while (resultSet.next()) {
                matchList.add(pickMatch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchList;
    }

    private MatchSet pickMatchSet(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_DATE = "date";
        String COL_TITLE = "title";

        MatchSet matchSet = new MatchSet(resultSet.getLong(COL_ID), resultSet.getDate(COL_DATE));
        matchSet.setTitle(resultSet.getString(COL_TITLE));

        List<Match> matches = getMatchesByMatchSetId(matchSet.getId());
        matchSet.setMatches(matches);

        return matchSet;
    }

    @Override
    public MatchSet getMatchSetById(long id) {

        int numId = 1;
        ResultSet resultSet;
        MatchSet matchSet = null;

        try {
            psSelectMatchSetById.setLong(numId, id);
            resultSet = psSelectMatchSetById.executeQuery();

            if (resultSet.next()) {
                matchSet = pickMatchSet(resultSet);
                matchSet.setMatches(getMatchesByMatchSetId(id));

                /*for (Match match: matchSet.getMatches()) {
                    matchSet.addAllEvaluations(evaluationDao.getEvaluationListByMatchId(match.getId()));
                } */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchSet;
    }

    @Override
    public MatchSet getMatchSetByDate(Date date) {

        int numDate = 1;
        ResultSet resultSet = null;
        MatchSet matchSet = null;

        try {
            psSelectMatchSetByDate.setDate(numDate, new java.sql.Date(date.getTime()));
            resultSet = psSelectMatchSetByDate.executeQuery();

            if (resultSet.next()) {
                matchSet = pickMatchSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchSet;
    }

    @Override
    public long getMaxMatchSetId() {

        String COL_MAX_ID = "max";
        ResultSet resultSet = null;

        try {

            resultSet = psSelectMaxMatchSetId.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(COL_MAX_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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
            psInsertMatch.setLong(numTeam1Id, match.getTeamFirst().getId());
            psInsertMatch.setLong(numTeam2Id, match.getTeamSecond().getId() );
            psInsertMatch.setInt(numTeam1Goals, match.getTeamFirstGoals());
            psInsertMatch.setInt(numTeam2Goals, match.getTeamSecondGoals());
            psInsertMatch.setDate(numDate, new java.sql.Date(match.getDate().getTime()));
            psInsertMatch.setTime(numTime, new java.sql.Time(match.getTime().getTime()));
            psInsertMatch.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addMatchToMatchSet(Match match, MatchSet matchSet) {


        int numMatchSetId = 1;
        int numMatchId = 2;

        List<Match> matchList = null;

        try {

            psInsertMatchToMatchSet.setLong(numMatchSetId, matchSet.getId());
            psInsertMatchToMatchSet.setLong(numMatchId, match.getId());
            psInsertMatchToMatchSet.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

        teamDao.close();
        //evaluationDao.close();

        closeConnection(psSelectMatches);
        closeConnection(psSelectMatchById);
        closeConnection(psDeleteMatchById);
        closeConnection(psInsertMatch);
        closeConnection(psSelectMaxId);
        closeConnection(psSelectLimitedNumMatchesFromId);
        closeConnection(psSelectLimitedNumMatchSetsFromId);
        closeConnection(psSelectMaxMatchSetId);
        closeConnection(psInsertMatchSet);
        closeConnection(psSelectMatchSetById);
        closeConnection(psInsertMatchToMatchSet);
        closeConnection(psSelectMatchesByMatchSetId);
        closeConnection(psSelectMatchSetByDate);
        closeConnection(connection);
    }
}
