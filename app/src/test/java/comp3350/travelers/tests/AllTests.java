package comp3350.travelers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.travelers.tests.business.AccessCitiesTest;
import comp3350.travelers.tests.business.AccessFlightTest;
import comp3350.travelers.tests.business.CalculateAirTicketPriceTest;
import comp3350.travelers.tests.business.SortTicketPriceHighToLowTest;
import comp3350.travelers.tests.business.SortTicketPriceLowToHighTest;
import comp3350.travelers.tests.business.ValidateInputTest;
import comp3350.travelers.tests.objects.CityTest;
import comp3350.travelers.tests.objects.FlightsTest;
import comp3350.travelers.tests.persistence.DatabaseTest;

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
