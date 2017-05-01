package comp3350.travelers.tests.business;

import junit.framework.TestCase;

import java.util.Collections;
import java.util.Comparator;

import java.util.ArrayList;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.business.AccessFlights;
import comp3350.travelers.business.SortTicketPricesHighToLow;
import comp3350.travelers.objects.Flight;
import comp3350.travelers.tests.persistence.DataBaseStub;

public class SortTicketPriceHighToLowTest extends TestCase {
    ArrayList<Flight> flightList;

    public SortTicketPriceHighToLowTest(String arg0) {
        super(arg0);
    }

    public void setUp() {
        flightList = new ArrayList<Flight>();
    }

    public void tearDown() {
        flightList = null;
    }

    public void testSortWithNullList() {
        try {
            Collections.sort(null, new SortTicketPricesHighToLow());
            fail("Null Pointer Exception Expected");
        } catch (NullPointerException e) {
        }
    }

    public void testSortWithNullInstance() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight(null);
        flightList.add(f1);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: null, End Date: null, Start Time: null, End Time: null, Company: null, Price: -1, Flight ID: null, Depart City: null, Arrive City: null, Duration: null",
                flightList.get(0).toString());
    }

    public void testSortWithNullInstances() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight(null);
        flightList.add(f1);
        Flight f2 = new Flight(null);
        flightList.add(f2);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: null, End Date: null, Start Time: null, End Time: null, Company: null, Price: -1, Flight ID: null, Depart City: null, Arrive City: null, Duration: null",
                flightList.get(0).toString());
        assertEquals("Start Data: null, End Date: null, Start Time: null, End Time: null, Company: null, Price: -1, Flight ID: null, Depart City: null, Arrive City: null, Duration: null",
                flightList.get(1).toString());
    }

    public void testSortWithListOfSingleValidItem() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs", flightList.get(0).toString());
    }

    public void testSortWithListOfSingleInvalidItem() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f = new Flight("","","","","",0,"","","","");
        flightList.add(f);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(0).toString());
    }

    public void testSortWithListOfInvalidItems() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("","","","","",0,"","","","");
        flightList.add(f1);
        Flight f2 = new Flight("","","","","",0,"","","","");
        flightList.add(f2);
        Flight f3 = new Flight("","","","","",0,"","","","");
        flightList.add(f3);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(0).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(1).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(2).toString());
    }

    public void testSortWithListOfValidAndInvalidItems() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("","","","","",0,"","","","");
        flightList.add(f1);
        Flight f2 = new Flight("","","","","",0,"","","","");
        flightList.add(f2);
        Flight f3 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 400, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f3);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 400, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs", flightList.get(0).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(1).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: 0, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(2).toString());
    }

    public void testSortWithListOfSingleInvalidItem2() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f = new Flight("","","","","",-1,"","","","");
        flightList.add(f);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(0).toString());
    }

    public void testSortWithListOfInvalidItems2() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("","","","","",-1,"","","","");
        flightList.add(f1);
        Flight f2 = new Flight("","","","","",-1,"","","","");
        flightList.add(f2);
        Flight f3 = new Flight("","","","","",-1,"","","","");
        flightList.add(f3);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(0).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(1).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(2).toString());
    }

    public void testSortWithListOfValidAndInvalidItems2() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("","","","","",-1,"","","","");
        flightList.add(f1);
        Flight f2 = new Flight("","","","","",-1,"","","","");
        flightList.add(f2);
        Flight f3 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 400, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f3);
        Collections.sort(flightList, new SortTicketPricesHighToLow());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 400, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs", flightList.get(0).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(1).toString());
        assertEquals("Start Data: , End Date: , Start Time: , End Time: , Company: , Price: -1, Flight ID: , Depart City: , Arrive City: , Duration: ", flightList.get(2).toString());
    }

    public void testSortWithListOfEqualItems() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f1);

        Flight f2 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f2);

        Flight f3 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f3);

        Collections.sort(flightList, new SortTicketPricesHighToLow());

        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(0).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(1).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(2).toString());
    }

    public void testSortWithListOfTwoEqualOneDifferentItems() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f1);

        Flight f2 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f2);

        Flight f3 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 400, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f3);

        Collections.sort(flightList, new SortTicketPricesHighToLow());

        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 400, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(0).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(1).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(2).toString());
    }

    public void testSortWithListOfItemsWithOnlyPriceDifference() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 600, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f1);

        Flight f2 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 450, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f2);

        Flight f3 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f3);

        Collections.sort(flightList, new SortTicketPricesHighToLow());

        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 600, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(0).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 450, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(1).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(2).toString());
    }

    public void testSortWithListOfItemsWithDifferentItems() {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight f1 = new Flight("02/24/2017", "02/24/2017", "1:00", "3:30", "Air Canada", 600, "AC 345", "Calgary","Winnipeg", "2.5hrs");
        flightList.add(f1);

        Flight f2 = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada", 450, "AC 450", "Winnipeg","Calgary", "2.5hrs");
        flightList.add(f2);

        Flight f3 = new Flight("05/22/2017", "05/22/2017", "1:00", "3:30", "Air Canada", 345, "AC 600", "Vancouver","Winnipeg", "3.5hrs");
        flightList.add(f3);

        Collections.sort(flightList, new SortTicketPricesHighToLow());

        assertEquals("Start Data: 02/24/2017, End Date: 02/24/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 600, Flight ID: AC 345, Depart City: Calgary, Arrive City: Winnipeg, Duration: 2.5hrs",
                flightList.get(0).toString());
        assertEquals("Start Data: 02/22/2017, End Date: 02/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 450, Flight ID: AC 450, Depart City: Winnipeg, Arrive City: Calgary, Duration: 2.5hrs",
                flightList.get(1).toString());
        assertEquals("Start Data: 05/22/2017, End Date: 05/22/2017, Start Time: 1:00, End Time: 3:30, Company: Air Canada, Price: 345, Flight ID: AC 600, Depart City: Vancouver, Arrive City: Winnipeg, Duration: 3.5hrs",
                flightList.get(2).toString());
    }


}
