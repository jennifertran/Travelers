package comp3350.travelers.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.objects.City;
import comp3350.travelers.objects.Flight;
import comp3350.travelers.persistence.Database;


public class DatabaseTest extends TestCase {

    private static String dbName = Main.dbName;


    public DatabaseTest(String arg0) {
        super(arg0);
    }

    public void testDatabase(){
        Database database;
        Services.closeDataAccess();

        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        database = Services.createDataAccess(new DataBaseStub(dbName));

        databaseTest();
        testGetFlightSequentialWithFilledList();
        testGetCitySequentialWithFilledList();
        testGetFlightSequentialWithNull();
        testGetCitySequentialWithNull();

        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public static void databaseTest(){
        Database database;
        List<Flight> flights;
        List<City> cities;

        Flight flight = null;
        City city = null;

        String result;

        System.out.println("\nStarting testNormalBehaviour");

        database = Services.getDataAccess(dbName);

        //testing for flights

        flights = new ArrayList<Flight>();
        result = database.getFlightSequential(flights);
        assertNotNull(flights);
        assertEquals(null,result);

        flight = flights.get(0);
        assertEquals("AC 345", flight.getFlightsID());
        flight = flights.get(1);
        assertEquals("AC 350", flight.getFlightsID());
        flight = flights.get(2);
        assertEquals("AC 355", flight.getFlightsID());
        flight = flights.get(3);
        assertEquals("AC 360", flight.getFlightsID());
        flight = flights.get(4);
        assertEquals("AC 365", flight.getFlightsID());
        flight = flights.get(5);
        assertEquals("AC 370", flight.getFlightsID());
        flight = flights.get(6);
        assertEquals("AC 375", flight.getFlightsID());
        flight = flights.get(7);
        assertEquals("AC 380", flight.getFlightsID());
        flight = flights.get(8);
        assertEquals("WR 350", flight.getFlightsID());
        flight = flights.get(9);
        assertEquals("WR 355", flight.getFlightsID());
        flight = flights.get(10);
        assertEquals("WS 459", flight.getFlightsID());
        flight = flights.get(11);
        assertEquals("WS 469", flight.getFlightsID());

        //testing for cities

        cities = new ArrayList<City>();
        result = database.getCitySequential(cities);
        assertEquals(null, result);
        assertNotNull(cities);

        city = cities.get(0);
        assertEquals("Calgary", city.getName());
        city = cities.get(1);
        assertEquals("Edmonton", city.getName());
        city = cities.get(2);
        assertEquals("Montreal", city.getName());
        city = cities.get(3);
        assertEquals("Ottawa", city.getName());
        city = cities.get(4);
        assertEquals("Toronto", city.getName());
        city = cities.get(5);
        assertEquals("Vancouver", city.getName());
        city = cities.get(6);
        assertEquals("Winnipeg", city.getName());

        System.out.println("Finished testNormalBehaviour");

    }

    public static void testGetFlightSequentialWithFilledList(){
        Database database;
        database = Services.getDataAccess(dbName);
        ArrayList<Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary", "Winnipeg", "2.5hrs"));
        assertEquals(null, database.getFlightSequential(flights));
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                ,flights.get(0).toString());
    }

    public static void testGetCitySequentialWithFilledList(){
        Database database;
        database = Services.getDataAccess(dbName);
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("Toronto", "YYZ", "Toronto Pearson International Airport"));
        assertEquals(null, database.getCitySequential(cities));
        assertEquals("City Name: Toronto, Airport Code:YYZ, Airport Name:Toronto Pearson International Airport"
                ,cities.get(0).toString());
    }

    public static void testGetFlightSequentialWithNull(){
        Database database;
        database = Services.getDataAccess(dbName);

        // Pass null as a parameter to getFlightSequential
        assertEquals("Passing Empty List",database.getFlightSequential(null));
    }

    public static void testGetCitySequentialWithNull(){
        Database database;
        database = Services.getDataAccess(dbName);

        // Pass null as a parameter to getCitySequential
        assertEquals("Passing Empty List",database.getCitySequential(null));
    }
}
