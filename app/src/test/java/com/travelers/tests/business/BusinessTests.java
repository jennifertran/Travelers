package com.travelers.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(CalculateAirTicketPriceTest.class);
        suite.addTestSuite(AccessFlightTest.class);
        suite.addTestSuite(AccessCitiesTest.class);
        suite.addTestSuite(ValidateInputTest.class);
        suite.addTestSuite(SortTicketPriceLowToHighTest.class);
        suite.addTestSuite(SortTicketPriceHighToLowTest.class);
        return suite;
    }
}
