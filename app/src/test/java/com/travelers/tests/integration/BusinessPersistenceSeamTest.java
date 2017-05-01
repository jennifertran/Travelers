package com.travelers.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;

import com.travelers.application.Main;
import com.travelers.application.Services;
import com.travelers.business.AccessCities;
import com.travelers.business.AccessFlights;
import com.travelers.objects.City;
import com.travelers.objects.Flight;

public class BusinessPersistenceSeamTest extends TestCase {
    public BusinessPersistenceSeamTest(String arg0) {
        super(arg0);
    }

    public void testGetFlightsInAccessFlights() {
        AccessFlights af;
        ArrayList<Flight> flights;
        Flight flight;

        flights = new ArrayList<Flight>();

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessFlights to persistence");
        // Before connect to the database

        Services.createDataAccess(Main.dbName);
        // After connect to the database
        af = new AccessFlights();
        af.getFlights(flights);
        flight = flights.get(0);
        assertNotNull(flight);
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("Calgary", flight.getDepartCityN());
        assertEquals("Winnipeg", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("1:00", flight.getStartTime());
        assertEquals("3:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());

        flight = flights.get(1);
        assertNotNull(flight);
        assertEquals("AC 350", flight.getFlightsID());
        assertEquals("Vancouver", flight.getDepartCityN());
        assertEquals("Edmonton", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("03/13/2017", flight.getStartData());
        assertEquals("03/13/2017", flight.getEndDate());
        assertEquals("3:00", flight.getStartTime());
        assertEquals("5:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(245, flight.getPrice());

        flight = flights.get(2);
        assertNotNull(flight);
        assertEquals("AC 355", flight.getFlightsID());
        assertEquals("Winnipeg", flight.getDepartCityN());
        assertEquals("Toronto", flight.getArriveCityN());
        assertEquals("6.5hrs", flight.getDuration());
        assertEquals("03/22/2017", flight.getStartData());
        assertEquals("03/22/2017", flight.getEndDate());
        assertEquals("8:00", flight.getStartTime());
        assertEquals("14:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(456, flight.getPrice());

        // the valid record in flights should be erased
        flights = new ArrayList<Flight>();
        flight = new Flight("02/28/2017", "02/28/2017", "6:00", "8:30", "Air Canada", 600, "AC 385", "Vancouver","Edmonton", "2.5hrs");
        flights.add(flight);
        assertNull(af.getFlights(flights));
        flight = flights.get(0);
        assertNotNull(flight);
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("Calgary", flight.getDepartCityN());
        assertEquals("Winnipeg", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("1:00", flight.getStartTime());
        assertEquals("3:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());

        // the invalid record in flights should be erased
        flights = new ArrayList<Flight>();
        flight = new Flight(null);
        flights.add(flight);
        assertNull(af.getFlights(flights));
        flight = flights.get(0);
        assertNotNull(flight);
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("Calgary", flight.getDepartCityN());
        assertEquals("Winnipeg", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("1:00", flight.getStartTime());
        assertEquals("3:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());

        // pass null list
        flights = null;
        assertEquals("Passing Empty List", af.getFlights(flights));

        Services.closeDataAccess();
    }

    public void testGetSequentialInAccessFlights() {
        AccessFlights af;
        Flight flight;


        Services.closeDataAccess();
        // Before connect to the database

        Services.createDataAccess(Main.dbName);
        // After connect to the database
        af = new AccessFlights();
        flight = af.getSequential();
        assertNotNull(flight);
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("Calgary", flight.getDepartCityN());
        assertEquals("Winnipeg", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("1:00", flight.getStartTime());
        assertEquals("3:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());

        flight = af.getSequential();
        assertNotNull(flight);
        assertEquals("AC 350", flight.getFlightsID());
        assertEquals("Vancouver", flight.getDepartCityN());
        assertEquals("Edmonton", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("03/13/2017", flight.getStartData());
        assertEquals("03/13/2017", flight.getEndDate());
        assertEquals("3:00", flight.getStartTime());
        assertEquals("5:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(245, flight.getPrice());

        flight = af.getSequential();
        assertNotNull(flight);
        assertEquals("AC 355", flight.getFlightsID());
        assertEquals("Winnipeg", flight.getDepartCityN());
        assertEquals("Toronto", flight.getArriveCityN());
        assertEquals("6.5hrs", flight.getDuration());
        assertEquals("03/22/2017", flight.getStartData());
        assertEquals("03/22/2017", flight.getEndDate());
        assertEquals("8:00", flight.getStartTime());
        assertEquals("14:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(456, flight.getPrice());

        for(int i=0; i<10; i++)
        {
            flight = af.getSequential();
        }
        assertNull(flight);

        flight = af.getSequential();
        assertNotNull(flight);
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("Calgary", flight.getDepartCityN());
        assertEquals("Winnipeg", flight.getArriveCityN());
        assertEquals("2.5hrs", flight.getDuration());
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("1:00", flight.getStartTime());
        assertEquals("3:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessFlights to persistence");
    }


    public void testGetCitiesInAccessCities() {
        AccessCities ac;
        ArrayList<City> cities;
        City city;


        cities = new ArrayList<City>();
        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessCities to persistence");

        // Before connect to the database
        Services.createDataAccess(Main.dbName);

        // After connect to the database
        ac = new AccessCities();
        assertNull(ac.getCities(cities));
        city = cities.get(0);
        assertNotNull(city);
        assertEquals("Calgary", city.getName());
        assertEquals("YYC", city.getCode());
        assertEquals("Calgary International Airport", city.getAirport());

        city = cities.get(1);
        assertNotNull(city);
        assertEquals("Edmonton", city.getName());
        assertEquals("YEG", city.getCode());
        assertEquals("Edmonton International Airport", city.getAirport());


        // the record in cities should be erased
        cities = new ArrayList<City>();
        city = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        cities.add(city);
        assertNull(ac.getCities(cities));
        city = cities.get(0);
        assertNotNull(city);
        assertEquals("Calgary", city.getName());
        assertEquals("YYC", city.getCode());
        assertEquals("Calgary International Airport", city.getAirport());

        // pass null list
        cities = null;
        assertEquals("Passing Empty List",ac.getCities(cities));

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessCities to persistence");
    }

    public void testGetSequentialInAccessCities() {
        AccessCities ac;
        City city;
        // Before connect to the database
        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessCities to persistence");

        Services.createDataAccess(Main.dbName);
        // After connect to the database
        ac = new AccessCities();
        city = ac.getSequential();
        assertNotNull(city);
        assertEquals("Calgary", city.getName());
        assertEquals("YYC", city.getCode());
        assertEquals("Calgary International Airport", city.getAirport());

        city = ac.getSequential();
        assertNotNull(city);
        assertEquals("Edmonton", city.getName());
        assertEquals("YEG", city.getCode());
        assertEquals("Edmonton International Airport", city.getAirport());

        city = ac.getSequential();
        assertNotNull(city);
        assertEquals("Montreal", city.getName());
        assertEquals("YMX", city.getCode());
        assertEquals("Montreal-Mirabel International Airport", city.getAirport());

        for(int i=0; i<5; i++)
        {
            city = ac.getSequential();
        }
        assertNull(city);

        city = ac.getSequential();
        assertNotNull(city);
        assertEquals("Calgary", city.getName());
        assertEquals("YYC", city.getCode());
        assertEquals("Calgary International Airport", city.getAirport());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessCities to persistence");
    }
}