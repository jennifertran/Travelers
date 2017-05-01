package comp3350.travelers.application;

public class Main {
    public static final String dbName = "travelers";
    private static String dbPathName = "database/TRAVELERS"; //hsqldb

    public static void main(String[] args) {
        startUp();

        shutDown();
        System.out.println("All done");
    }

    public static void startUp() {
        Services.createDataAccess(dbName);

    }

    public static void shutDown() {
        Services.closeDataAccess();
    }

    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    } //hsqldb

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    } //hsqldb
}
