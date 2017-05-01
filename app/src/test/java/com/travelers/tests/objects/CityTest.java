package com.travelers.tests.objects;

import junit.framework.TestCase;

import com.travelers.objects.City;

public class CityTest extends TestCase {

    public CityTest(String arg0) {
        super(arg0);
    }

    public void testNormalBehaviour() {
        City city = null;
        City city2 = null;

        System.out.println("\nStarting testNormalBehaviour");

        city = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        assertNotNull(city);
        assertEquals("Winnipeg", city.getName());
        assertEquals("YWG", city.getCode());
        assertEquals("Winnipeg James Armstrong Richardson International Airport", city.getAirport());

        city2 = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        // test isEqual method
        assertEquals(true, city.isEqual(city2));

        city2 = new City("Victoria", "YYJ", "Victoria International Airport");

        assertEquals(false, city.isEqual(city2));

        city.setName("Victoria");
        assertEquals("Victoria", city.getName());

        city.setCode("YYJ");
        assertEquals("YYJ", city.getCode());

        city.setAirport("Victoria International Airport");
        assertEquals("Victoria International Airport", city.getAirport());

        System.out.println("Finished testNormalBehaviour");
    }

    public void testEdgeCases() {
        City city = null;
        City city2 = null;

        System.out.println("\nStarting testEdgeCases");

        // The case in which null value is assigned to one of the fields
        // Not the same is expected
        city = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        city2 = new City("Winnpeg", "YWG", null);
        assertEquals(false, city.isEqual(city2));

        // The same fields of two flights instance are both null
        // Not the same is expected because, by definition, the city is invalid
        city.setAirport(null);
        assertEquals(false, city.isEqual(city2));

        // The second flight is null
        // Not the same is expected because, by definition, the city is invalid
        city2 = null;
        assertEquals(false, city.isEqual(city2));

        System.out.println("Finished testEdgeCases");
    }

}
