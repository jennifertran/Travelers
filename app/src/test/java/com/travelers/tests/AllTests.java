package com.travelers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.travelers.tests.business.AccessCitiesTest;
import com.travelers.tests.business.AccessFlightTest;
import com.travelers.tests.business.CalculateAirTicketPriceTest;
import com.travelers.tests.business.SortTicketPriceHighToLowTest;
import com.travelers.tests.business.SortTicketPriceLowToHighTest;
import com.travelers.tests.business.ValidateInputTest;
import com.travelers.tests.objects.CityTest;
import com.travelers.tests.objects.FlightsTest;
import com.travelers.tests.persistence.DatabaseTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(CityTest.class);
        suite.addTestSuite(FlightsTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(CalculateAirTicketPriceTest.class);
        suite.addTestSuite(AccessFlightTest.class);
        suite.addTestSuite(AccessCitiesTest.class);
        suite.addTestSuite(ValidateInputTest.class);
        suite.addTestSuite(SortTicketPriceLowToHighTest.class);
        suite.addTestSuite(SortTicketPriceHighToLowTest.class);
    }

    private static void testPersistence() {
        suite.addTestSuite(DatabaseTest.class);
    }
}
