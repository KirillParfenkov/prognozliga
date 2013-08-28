package by.parf.checkers.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 17.8.13
 * Time: 14.58
 */
public class PropertyManager {

    private static String queryPath = "src/main/resources/queries.properties";
    private static Properties sqlProperties = null;
    private static String databasePath = "src/main/resources/database.properties";
    private static Properties dbProperties = null;

    private  PropertyManager() throws IOException {
    }

    public static Properties getSqlProperties() {

        if (sqlProperties == null) {
            File file = new File(queryPath);
            sqlProperties = new Properties();

            try {
                sqlProperties.load(new FileInputStream(file));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sqlProperties;
    }

    public static Properties getDatabaseProperties() {

        if (dbProperties == null) {
            File file = new File(databasePath);
            dbProperties = new Properties();

            try {
                dbProperties.load(new FileInputStream(file));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dbProperties;
    }

}
