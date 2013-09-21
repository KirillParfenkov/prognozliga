package by.parf.checkers.dao;

import by.parf.checkers.beans.Match;
import by.parf.checkers.beans.MatchSet;

import java.util.Date;
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
    void addMatchToMatchSet(Match match, MatchSet matchSet);
    List<Match> getLimitedMatchListFromId(long fromId, long number);
    List<MatchSet> getLimitedMatchSetListFromId(long fromId, long number);
    void addMatchSet(MatchSet matchSet);
    MatchSet getMatchSetById(long id);
    MatchSet getMatchSetByDate(Date date);
    long getMaxMatchSetId();
    void close();

}
