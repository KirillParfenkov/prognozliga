package by.parf.checkers.beans;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 19.9.13
 * Time: 19.54
 */
public class MatchSet {

    private long id;
    private Date date;
    private String title;
    private List<Match> matches;
    private List<Evaluation> evaluations;
    private Map<User, Map<Match, Evaluation>> userList;
    private Boolean closed;

    public MatchSet(long id, Date date) {
        this.id = id;
        this.date = date;
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
        userList = new HashMap<User, Map<Match, Evaluation>>();
        closed = false;
    }

    public MatchSet() {
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
        userList = new HashMap<User, Map<Match, Evaluation>>();
        closed = false;
    }

    public MatchSet(Date date) {
        this.date = date;
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
        userList = new HashMap<User, Map<Match, Evaluation>>();
        closed = false;
    }

    public MatchSet(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void addAllEvaluations(List<Evaluation> evaluations) {
        this.evaluations.addAll(evaluations);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<User, Map<Match, Evaluation>> getUserList() {
        return userList;
    }

    public void setUserList(Map<User, Map<Match, Evaluation>> userList) {
        this.userList = userList;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
