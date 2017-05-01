package comp3350.travelers.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.business.AccessCities;
import comp3350.travelers.objects.City;
import comp3350.travelers.tests.persistence.DataBaseStub;

public class AccessCitiesTest extends TestCase {
    AccessCities a;
    private static String dbName = Main.dbName;

    public AccessCitiesTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(new DataBaseStub(dbName));
        a = new AccessCities();
    }

    public void tearDown() {
        a = null;
        Services.closeDataAccess();
    }

    public void testGetCitiesWithEmptyList() {
        ArrayList<City> cities = new ArrayList<City>();
        assertEquals(null, a.getCities(cities));
        assertEquals(7, cities.size());
        // Make sure the returned array list contains all the instances
        assertEquals("City Name: Calgary, Airport Code:YYC, Airport Name:Calgary International Airport"
                , cities.get(0).toString());
        assertEquals("City Name: Winnipeg, Airport Code:YWG, Airport Name:Winnipeg James Armstrong Richardson International Airport"
                , cities.get(cities.size() - 1).toString());

    }

    public void testGetCitiesWithFilledList() {
        ArrayList<City> cities = new ArrayList<City>();
        // Make sure no mater there is instance in cities array list, after use getCities()
        // they will be destroyed and the instances in database will go in the list
        cities.add(new City("Ottawa"));
        assertEquals(null, a.getCities(cities));
        assertEquals(7, cities.size());
        assertEquals("City Name: Calgary, Airport Code:YYC, Airport Name:Calgary International Airport"
                , cities.get(0).toString());

    }

    public void testGetCitiesWithListNullInstance() {
        ArrayList<City> cities = new ArrayList<City>();
        // Make sure no mater there is null instance in cities array list, after use getCities()
        // they will be destroyed and the instances in database will go in the list
        cities.add(null);
        assertEquals(null, a.getCities(cities));
        assertEquals(7, cities.size());
        assertEquals("City Name: Calgary, Airport Code:YYC, Airport Name:Calgary International Airport"
                , cities.get(0).toString());
    }

    public void testGetCitiesWithNull() {
        assertEquals("Passing Empty List", a.getCities(null));
    }

    public void testGetSequentialInNormalSequence() {
        assertEquals("City Name: Calgary, Airport Code:YYC, Airport Name:Calgary International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Edmonton, Airport Code:YEG, Airport Name:Edmonton International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Montreal, Airport Code:YMX, Airport Name:Montreal-Mirabel International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Ottawa, Airport Code:YOW, Airport Name:Ottawa Macdonald-Cartier International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Toronto, Airport Code:YYZ, Airport Name:Toronto Pearson International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Vancouver, Airport Code:YVR, Airport Name:Vancouver International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Winnipeg, Airport Code:YWG, Airport Name:Winnipeg James Armstrong Richardson International Airport"
                , a.getSequential().toString());

    }

    public void testGetSequentialLoopBack() {
        for (int i = 0; i < 7; i++) {
            a.getSequential();
        }

        // after iterating through all instances, a gets null
        try {
            a.getSequential().toString();
            fail("Null Pointer Error Expected");
        } catch (Exception e) {
        }
        // loop back to first instance
        assertEquals("City Name: Calgary, Airport Code:YYC, Airport Name:Calgary International Airport"
                , a.getSequential().toString());
        assertEquals("City Name: Edmonton, Airport Code:YEG, Airport Name:Edmonton International Airport"
                , a.getSequential().toString());

    }
}

