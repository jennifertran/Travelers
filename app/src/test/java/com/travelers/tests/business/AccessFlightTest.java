package com.travelers.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import com.travelers.application.Main;
import com.travelers.application.Services;
import com.travelers.business.AccessFlights;
import com.travelers.objects.Flight;
import com.travelers.tests.persistence.DataBaseStub;

public class AccessFlightTest extends TestCase {
    AccessFlights a;
    private static String dbName = Main.dbName;

    public AccessFlightTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(new DataBaseStub(dbName));
        a = new AccessFlights();
    }

    public void tearDown() {
        a = null;
        Services.closeDataAccess();
    }

    public void testGetFlightsWithEmptyList() {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        assertEquals(null, a.getFlights(flights));

        assertEquals(12, flights.size());
        // Make sure the returned array list contains all the instances
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                , flights.get(0).toString());
        assertEquals("Start Data: 04/18/2017, End Date: 04/18/2017, Start Time: 15:00, End Time: 16:30, Company: West Jet, Price: 925, Flight ID: WS 469, Depart City: Toronto, Arrive City: Edmonton, Duration: 1.5hrs"
                , flights.get(flights.size() - 1).toString());
    }

    public void testGetFlightsWithFilledList() {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        // Make sure no mater there is instance in flights array list, after use getFlights()
        // they will be destroyed and the instances in database will go in the list
        flights.add(new Flight("AC 355"));
        assertEquals(null, a.getFlights(flights));
        assertEquals(12, flights.size());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                , flights.get(0).toString());
        assertEquals("Start Data: 04/18/2017, End Date: 04/18/2017, Start Time: 15:00, End Time: 16:30, Company: West Jet, Price: 925, Flight ID: WS 469, Depart City: Toronto, Arrive City: Edmonton, Duration: 1.5hrs"
                , flights.get(flights.size() - 1).toString());

    }

    public void testGetFlightsWithListNullInstance() {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        // Make sure no mater there is null instance in flights array list, after use getFlights()
        // they will be destroyed and the instances in database will go in the list
        flights.add(null);
        assertEquals(null, a.getFlights(flights));
        assertEquals(12, flights.size());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                , flights.get(0).toString());
        assertEquals("Start Data: 04/18/2017, End Date: 04/18/2017, Start Time: 15:00, End Time: 16:30, Company: West Jet, Price: 925, Flight ID: WS 469, Depart City: Toronto, Arrive City: Edmonton, Duration: 1.5hrs"
                , flights.get(flights.size() - 1).toString());

    }

    public void testGetFlightsWithNull() {
        assertEquals("Passing Empty List", a.getFlights(null));
    }

    public void testGetSequentialInNormalSequence() {
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 03/13/2017, End Date: 03/13/2017, Start Time: 3:00, End Time: 5:30, Company: Air Canada, Price: 245, Flight ID: AC 350, Depart City: Vancouver, Arrive City: Edmonton, Duration: 2.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 03/22/2017, End Date: 03/22/2017, Start Time: 8:00, End Time: 14:30, Company: Air Canada, Price: 456, Flight ID: AC 355, Depart City: Winnipeg, Arrive City: Toronto, Duration: 6.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 03/28/2017, End Date: 03/28/2017, Start Time: 11:00, End Time: 15:30, Company: Air Canada, Price: 145, Flight ID: AC 360, Depart City: Vancouver, Arrive City: Toronto, Duration: 4.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 04/04/2017, End Date: 04/04/2017, Start Time: 11:30, End Time: 15:30, Company: Air Canada, Price: 643, Flight ID: AC 365, Depart City: Winnipeg, Arrive City: Montreal, Duration: 4hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 05/09/2017, End Date: 05/09/2017, Start Time: 16:00, End Time: 18:30, Company: Air Canada, Price: 475, Flight ID: AC 370, Depart City: Winnipeg, Arrive City: Montreal, Duration: 2.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 05/26/2017, End Date: 05/26/2017, Start Time: 18:30, End Time: 21:30, Company: Air Canada, Price: 893, Flight ID: AC 375, Depart City: Montreal, Arrive City: Ottawa, Duration: 3.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 05/29/2017, End Date: 05/29/2017, Start Time: 22:00, End Time: 13:30, Company: Air Canada, Price: 789, Flight ID: AC 380, Depart City: Ottawa, Arrive City: Toronto, Duration: 1.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 02/25/2017, End Date: 02/25/2017, Start Time: 2:00, End Time: 7:00, Company: WestJet Encore, Price: 367, Flight ID: WR 350, Depart City: Winnipeg, Arrive City: Vancouver, Duration: 5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 04/12/2017, End Date: 04/12/2017, Start Time: 13:00, End Time: 15:30, Company: WestJet Encore, Price: 645, Flight ID: WR 355, Depart City: Montreal, Arrive City: Vancouver, Duration: 2.5hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 03/02/2017, End Date: 03/02/2017, Start Time: 3:00, End Time: 6:30, Company: West Jet, Price: 315, Flight ID: WS 459, Depart City: Toronto, Arrive City: Ottawa, Duration: 3hrs"
                , a.getSequential().toString());
        assertEquals("Start Data: 04/18/2017, End Date: 04/18/2017, Start Time: 15:00, End Time: 16:30, Company: West Jet, Price: 925, Flight ID: WS 469, Depart City: Toronto, Arrive City: Edmonton, Duration: 1.5hrs"
                , a.getSequential().toString());
    }

    public void testGetSequentialLoopBack() {
        for (int i = 0; i < 12; i++) {
            a.getSequential();
        }

        // after iterating through all instances, a gets null
        try {
            a.getSequential().toString();
            fail("Null Pointer Error Expected");
        } catch (Exception e) {
        }
        // loop back to first instance
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs"
                , a.getSequential().toString());
        // get second instance
        assertEquals("Start Data: 03/13/2017, End Date: 03/13/2017, Start Time: 3:00, End Time: 5:30, Company: Air Canada, Price: 245, Flight ID: AC 350, Depart City: Vancouver, Arrive City: Edmonton, Duration: 2.5hrs"
                , a.getSequential().toString());
    }
}

