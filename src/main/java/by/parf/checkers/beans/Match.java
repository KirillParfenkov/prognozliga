package by.parf.checkers.beans;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 28.8.13
 * Time: 21.04
 */
public class Match {

    private long id;
    private String name;
    private Team teamFirst;
    private Team teamSecond;
    private int teamFirstGoals;
    private int teamSecondGoals;

    public Match(long id) {
        this.id = id;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeamFirst() {
        return teamFirst;
    }

    public void setTeamFirst(Team teamFirst) {
        this.teamFirst = teamFirst;
    }

    public Team getTeamSecond() {
        return teamSecond;
    }

    public void setTeamSecond(Team teamSecond) {
        this.teamSecond = teamSecond;
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
