package by.parf.checkers.dao;

import by.parf.checkers.beans.User;

/**
 * User: Kiryl_Parfiankou
 * Date: 8/1/13
 * Time: 11:18 AM
 */
public interface UserDao {

    boolean authenticate(User user, String password);
    User getUserById(long id);
    User getUserByEmail(String email);
    User removeUserById(long id);
    User updateUser(User user);
    void close();
}
