package comp3350.travelers.application;

import comp3350.travelers.persistence.Database;
import comp3350.travelers.persistence.DatabaseObject;

public class Services {
    private static Database dataAccessService = null;

    public static Database createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DatabaseObject(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static Database createDataAccess(Database alternateDataAccessService) {
        if (dataAccessService == null) {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static Database getDataAccess(String dbName) {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}

