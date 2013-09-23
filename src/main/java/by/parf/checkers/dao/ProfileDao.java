package by.parf.checkers.dao;

import by.parf.checkers.beans.Profile;

/**
 * User: Kiryl_Parfiankou
 * Date: 8/1/13
 * Time: 11:18 AM
 */
public interface ProfileDao {

    Profile getProfileById(long profileId);
    void close();

}
