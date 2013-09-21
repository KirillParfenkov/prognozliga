package by.parf.checkers.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public MatchSet(long id, Date date) {
        this.id = id;
        this.date = date;
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
    }

    public MatchSet() {
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
    }

    public MatchSet(Date date) {
        this.date = date;
        matches = new ArrayList<Match>();
        evaluations = new ArrayList<Evaluation>();
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
}
