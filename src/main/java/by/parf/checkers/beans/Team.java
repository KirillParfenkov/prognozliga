package by.parf.checkers.beans;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 28.8.13
 * Time: 21.10
 */

public class Team {

    private long id;
    private String name;

    public Team(long id) {
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
}
