package by.parf.checkers.beans;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 28.8.13
 * Time: 21.02
 */
public class Evaluation {

    private long id;
    private User user;
    private Match match;
    private int teamFirstGoals;
    private int teamSecondGoals;

    public Evaluation(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getTeamFirstGoals() {
        return teamFirstGoals;
    }

    public void setTeamFirstGoals(int teamFirstGoals) {
        this.teamFirstGoals = teamFirstGoals;
    }

    public int getTeamSecondGoals() {
        return teamSecondGoals;
    }

    public void setTeamSecondGoals(int teamSecondGoals) {
        this.teamSecondGoals = teamSecondGoals;
    }
}
