package com.travelers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.travelers.tests.business.BusinessTests;
import com.travelers.tests.objects.ObjectTests;
import com.travelers.tests.persistence.PersistenceTests;

public class RunUnitTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Unit tests");
        suite.addTest(ObjectTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}
