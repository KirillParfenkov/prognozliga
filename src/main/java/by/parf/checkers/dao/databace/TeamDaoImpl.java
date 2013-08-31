package by.parf.checkers.dao.databace;

import by.parf.checkers.beans.Team;
import by.parf.checkers.dao.TeamDao;
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
 * Date: 31.8.13
 * Time: 19.24
 */
public class TeamDaoImpl extends AbstractDatabaseDAO implements TeamDao{

    private static String SELECT_TEAMS = "select.teams";
    private static String SELECT_TEAM_BY_ID =  "select.team.by.id";
    private static String DELETE_TEAM_BY_ID =  "delete.team.by.id";
    private static String INSERT_TEAM = "insert.team";
    private static String SELECT_TEAM_MAX_ID = "select.max.team.id";

    private Connection connection;
    private PreparedStatement psSelectTeams;
    private PreparedStatement psSelectTeamById;
    private PreparedStatement psDeleteTeamById;
    private PreparedStatement psInsertTeam;
    private PreparedStatement psSelectMaxTeamId;

    public TeamDaoImpl() {

        try {

            connection = getConnection();
            initQuerys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initQuerys() throws SQLException {

        Properties props = PropertyManager.getSqlProperties();

        psSelectTeams = connection.prepareStatement(props.getProperty(SELECT_TEAMS));
        psSelectTeamById = connection.prepareStatement(props.getProperty(SELECT_TEAM_BY_ID));
        psDeleteTeamById = connection.prepareStatement(props.getProperty(DELETE_TEAM_BY_ID));
        psInsertTeam = connection.prepareStatement(props.getProperty(INSERT_TEAM));
        psSelectMaxTeamId = connection.prepareStatement(props.getProperty(SELECT_TEAM_MAX_ID));

    }

    private Team pickTeam(ResultSet resultSet) throws SQLException {

        String COL_ID = "id";
        String COL_NAME = "name";

        Team team = new Team(resultSet.getLong(COL_ID));
        team.setName(resultSet.getString(COL_NAME));

        return team;
    }



    @Override
    public Team getTeamById(long id) {

        int numId = 1;
        ResultSet resultSet = null;

        try {

            psSelectTeamById.setLong(numId, id);
            resultSet =  psSelectTeamById.executeQuery();

            if (resultSet.next()) {
                return pickTeam(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Team> getTeamList() {

        ResultSet resultSet = null;
        List<Team> teamList = new ArrayList<Team>();

        try {
            resultSet = psSelectTeams.executeQuery();

            while (resultSet.next()) {
                teamList.add(pickTeam(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamList;

    }

    @Override
    public long getMaxId() {

        String COL_MAX_ID = "max";
        ResultSet resultSet = null;

        try {

            resultSet = psSelectMaxTeamId.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(COL_MAX_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void removeTeam(Team team) {

        int numId = 1;
        ResultSet resultSet = null;

        try {

            psDeleteTeamById.setLong(numId, team.getId());
            psDeleteTeamById.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeam(Team team) {


    }

    @Override
    public void addTeam(Team team) {

        int numId = 1;
        int numName = 2;

        try {

            psInsertTeam.setLong(numId, team.getId());
            psInsertTeam.setString(numName, team.getName());
            psInsertTeam.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        closeConnection(psSelectTeams);
        closeConnection(psSelectTeamById);
        closeConnection(psDeleteTeamById);
        closeConnection(psInsertTeam);
        closeConnection(psSelectMaxTeamId);
        closeConnection(connection);
    }
}
