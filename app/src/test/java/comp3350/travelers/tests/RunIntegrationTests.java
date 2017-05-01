package comp3350.travelers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.travelers.tests.integration.IntegrationTests;

public class RunIntegrationTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Integration tests");
        suite.addTest(IntegrationTests.suite());
        return suite;
    }
}
