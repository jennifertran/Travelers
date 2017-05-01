package com.travelers.tests.objects;

import junit.framework.TestCase;

import com.travelers.objects.City;
import com.travelers.objects.Flight;

public class FlightsTest extends TestCase {

    public FlightsTest(String arg0) {
        super(arg0);
    }

    public void testNormalBehaviour() {
        Flight flight;
        Flight flight2;
        City calgary = new City("Calgary", "YYC", "Calgary International Airport");
        City winnipeg = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");

        System.out.println("\nStarting testNormalBehaviour");

        flight = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary, winnipeg, "2.5hrs");
        assertNotNull(flight);
        assertEquals("02/22/2017", flight.getStartData());
        assertEquals("02/22/2017", flight.getEndDate());
        assertEquals("13:00", flight.getStartTime());
        assertEquals("15:30", flight.getEndTime());
        assertEquals("Air Canada", flight.getCompany());
        assertEquals(345, flight.getPrice());
        assertEquals("AC 345", flight.getFlightsID());
        assertEquals("2.5hrs", flight.getDuration());

        // test isEqual method
        flight2 = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary, winnipeg, "2.5hrs");
        assertEquals(true, flight.isEqual(flight2));

        flight2 = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary, winnipeg, "3.5hrs");
        assertEquals(false, flight.isEqual(flight2));

        flight.setStartData("02/25/2017");
        assertEquals("02/25/2017", flight.getStartData());

        flight.setEndDate("02/25/2017");
        assertEquals("02/25/2017", flight.getEndDate());

        flight.setStartTime("18:00");
        assertEquals("18:00", flight.getStartTime());

        flight.setEndTime("21:30");
        assertEquals("21:30", flight.getEndTime());

        flight.setFlightsID("AC 245");
        assertEquals("AC 245", flight.getFlightsID());

        flight.setPrice(245);
        assertEquals(245, flight.getPrice());

        flight.setDepartCity(winnipeg);
        assertEquals(true, flight.getDepartCity().isEqual(winnipeg));

        flight.setArriveCity(calgary);
        assertEquals(true, flight.getArriveCity().isEqual(calgary));

        flight.setDuration("3.5hrs");
        assertEquals("3.5hrs", flight.getDuration());

        flight.setCompany("WestJet");
        assertEquals("WestJet", flight.getCompany());

        // Test whether city field is compared properly
        // Expecting two instances to be not equal
        flight = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary, winnipeg, "3.5hrs");
        City calgary2 = new City("Calgary", "YYC", "Calgary Airport");
        flight2 = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary2, winnipeg, "3.5hrs");
        assertEquals(false, flight.isEqual(flight2));

        System.out.println("Finished testNormalBehaviour");

    }

    public void testEdgeCases() {
        Flight flight;
        Flight flight2;

        System.out.println("\nStarting testEdgeCases");

        // Null value is assigned to one of the fields
        // Not the same is expected
        City calgary = new City("Calgary", "YYC", "Calgary International Airport");
        City winnipeg = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        flight = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", "Air Canada", 345, "AC 345", calgary, winnipeg, "2.5hrs");
        flight2 = new Flight("02/22/2017", "02/22/2017", "13:00", "15:30", null, 345, "AC 345", calgary, winnipeg, "2.5hrs");
        assertEquals(false, flight.isEqual(flight2));

        // The same fields of two flights instance are both null
        // Not the same is expected because, by definition, the flight is invalid
        flight.setFlightsID(null);
        assertEquals(false, flight.isEqual(flight2));

        // -1 is assigned to the price field
        // Not the same is expected because, by definition, the flight is invalid
        flight.setFlightsID("AC 345");
        flight2.setPrice(-1);
        assertEquals(false, flight.isEqual(flight2));

        // -1 is assigned to each price field of both instances
        // Not the same is expected because, by definition, the flight is invalid
        flight.setPrice(-1);
        assertEquals(false, flight.isEqual(flight2));

        // null is assigned to each flight id field of both instances
        // Not the same is expected because, by definition, the flight is invalid
        flight.setPrice(235);
        flight2.setPrice(235);
        flight.setFlightsID(null);
        flight2.setFlightsID(null);
        assertEquals(false, flight.isEqual(flight2));

        // The second flight is null
        // Not the same is expected because, by definition, the flight is invalid
        flight2 = null;
        assertEquals(false, flight.isEqual(flight2));
        System.out.println("Finished testEdgeCases");
    }
}