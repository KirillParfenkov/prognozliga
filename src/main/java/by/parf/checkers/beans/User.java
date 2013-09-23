package by.parf.checkers.beans;

/**
 * User: Kiryl_Parfiankou
 * Date: 8/1/13
 * Time: 11:11 AM
 */
public class User implements Comparable <User>{

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String purse;
    private boolean isActive;
    private Profile profile;

    public User(long id) {
        this.id = id;
    }

    //  gets and sets
    public void setId( long id ) {
        this.id = id;
    }
    public void setEmail( String email ) {
        this.email = email;
    }
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public void setProfile( Profile profile) {
        this.profile = profile;
    }
    public void setPurse( String purse ) {
        this.purse = purse;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Profile getProfile() {
        return profile;
    }
    public String getPurse() {
        return purse;
    }
    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if(!(obj instanceof User))
            return false;
        User user = (User) obj;

        return this.compareTo(user) == 0;
    }

    @Override
    public int compareTo(User user) {

        if (this.getId() < user.getId()) {
            return -1;
        }
        if (this.getId() > user.getId()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return (int) (id % 999999);
    }
}