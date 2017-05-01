
package com.travelers.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.travelers.objects.City;
import com.travelers.objects.Flight;


public class DatabaseObject implements Database {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Flight> flights;
    private ArrayList<City> cities;


    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DatabaseObject(String dbName) {
        this.dbName = dbName;
    }

    public void open(String dbPath) {
        String url;
        try {
            System.out.println("*** OPENING DB");
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close() {
        try {  // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getFlightSequential(List<Flight> flightResult) {
        Flight flight;
        String myStartDate, myEndDate, myStartTime, myEndTime, myCompany;
        int myPrice;
        String myID;
        String myDepartCity, myArriveCity;
        String myDuration;
        myStartDate = EOF;
        myEndDate = EOF;
        myStartTime = EOF;
        myEndTime = EOF;
        myCompany = EOF;
        myID = EOF;
        myPrice = 0;
        myDepartCity = EOF;
        myArriveCity = EOF;
        myDuration = EOF;

        result = null;
        try {
            cmdString = "Select * from Flight";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            result = "Can't Find Flight Table";
        }

        if (rs2!=null)
        {
            try {
                while ( flightResult!=null && rs2.next()) {
                    myID = rs2.getString("FlightID");
                    myStartDate = rs2.getString("Startdate");
                    myEndDate = rs2.getString("Enddate");
                    myStartTime = rs2.getString("Starttime");
                    myEndTime = rs2.getString("Endtime");
                    myCompany = rs2.getString("Company");
                    myPrice = rs2.getInt("Price");
                    myDepartCity = rs2.getString("Departcity");
                    myArriveCity = rs2.getString("Arrivecity");
                    myDuration = rs2.getString("Duration");

                    flight = new Flight(myStartDate, myEndDate, myStartTime, myEndTime, myCompany, myPrice,
                            myID, myDepartCity, myArriveCity, myDuration);
                    flightResult.add(flight);
                }
                if(flightResult == null)
                    result = "Passing Empty List";
                rs2.close();
            } catch (Exception e) {
                result = "Can't Find Certain Field(s)";
            }
        }

        return result;
    }

    public String getCitySequential(List<City> cityResult) {
        City city;
        String myName, myCode, myAirpot;
        myName = EOF;
        myCode = EOF;
        myAirpot = EOF;

        result = null;
        try {
            cmdString = "Select * from City";
            rs5 = st3.executeQuery(cmdString);
            // ResultSetMetaData md5 = rs5.getMetaData();
        } catch (Exception e) {
            result = "Can't Find City Table";
        }
        if(rs5!=null)
        {
            try{
                while (cityResult!=null && rs5.next()) {
                    myName = rs5.getString("Name");
                    myCode = rs5.getString("Code");
                    myAirpot = rs5.getString("Airport");

                    city = new City(myName, myCode, myAirpot);
                    cityResult.add(city);
                }
                if(cityResult == null)
                    result = "Passing Empty List";
                rs5.close();
            }catch(Exception e){
                result = "Can't Find Certain Field(s)";
            }
        }
        return result;
    }

    public String checkWarning(Statement st, int updateCount) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        if (updateCount != 1) {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}


