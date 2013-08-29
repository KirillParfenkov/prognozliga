package by.parf.checkers.dao;

import by.parf.checkers.beans.Match;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 30.8.13
 * Time: 0.48
 */
public interface MatchDao {

    Match getMatchById(long id);
    List<Match> getMatchList();
    long getMaxId();
    void removeMatch(Match match);
    void updateMatch(Match match);
    void addMatch(Match match);
    void close();

}
