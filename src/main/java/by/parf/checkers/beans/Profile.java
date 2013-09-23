package by.parf.checkers.beans;

/**
 * User: Kiryl_Parfiankou
 * Date: 8/1/13
 * Time: 11:11 AM
 */
public class Profile {
    private long id;
    private String name;

    public Profile(long id){
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
