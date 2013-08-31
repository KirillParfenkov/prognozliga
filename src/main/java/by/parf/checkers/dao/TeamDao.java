package by.parf.checkers.dao;


import by.parf.checkers.beans.Team;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 31.8.13
 * Time: 19.23
 */
public interface TeamDao {

    Team getTeamById(long id);
    List<Team> getTeamList();
    long getMaxId();
    void removeTeam(Team team);
    void updateTeam(Team team);
    void addTeam(Team team);
    void close();

}
