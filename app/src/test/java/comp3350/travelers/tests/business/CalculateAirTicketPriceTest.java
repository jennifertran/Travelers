package comp3350.travelers.tests.business;

import junit.framework.TestCase;

import comp3350.travelers.business.CalculateAirTicketPrice;
import comp3350.travelers.objects.Flight;


public class CalculateAirTicketPriceTest extends TestCase {

    private Flight flightInfo = null; //Flight reservation

    public void testNoInfo() {
        flightInfo = new Flight(0, 0, 0, 0);
        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testNullList() {
        try {
            CalculateAirTicketPrice.ticketPrice(null);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }
    }

    // Testing invalid data error for the case in which no adult is flying
    // e.g. a child is not accompanied by an adult

    public void testPriceNoAdults() {
        // There should atleast be one adult so the will be considered invalid data

        flightInfo = new Flight(400, 0, 1, 1);
        // first parameter is the ticket price
        // second is the number of adults
        // third is the number of children
        // forth is the number of bags

        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    // Testing the total ticketPrice for adults

    public void testPriceOneAdult() {

        flightInfo = new Flight(400, 1, 0, 0);

        assertEquals(438.0, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(582.45, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(947.32, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }


    public void testPriceTwoAdult() {

        flightInfo = new Flight(400, 2, 0, 0);
        assertEquals(866.0, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(1154.9, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(1884.64, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }


    // Testing the total ticketPrice after the addition of the children

    public void testPriceOneChild() {

        flightInfo = new Flight(400, 1, 1, 0);
        assertEquals(652.0, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(868.68, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(1415.98, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }


    public void testPriceTwoChild() {

        flightInfo = new Flight(400, 1, 2, 0);
        assertEquals(866.0, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(1154.9, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(1884.64, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }

    // Testing typical flight reservations

    public void testTypicalFlights() {

        flightInfo = new Flight(1556, 1, 0, 2);

        assertEquals(1739.12, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo = new Flight(976, 5, 0, 5);
        assertEquals(5392.1, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo = new Flight(1084, 1, 2, 5);
        assertEquals(2490.26, CalculateAirTicketPrice.ticketPrice(flightInfo));

    }

    // Testing the total ticketPrice after the addition of the bags

    public void testPriceOneBag() {

        flightInfo = new Flight(400, 1, 0, 1);
        assertEquals(470.1, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(614.55, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(979.42, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }

    public void testPriceTwoBags() {

        flightInfo = new Flight(400, 1, 0, 2);
        assertEquals(502.2, CalculateAirTicketPrice.ticketPrice(flightInfo));

        //Testing with different ticket prices
        flightInfo.setPrice(535);
        assertEquals(646.65, CalculateAirTicketPrice.ticketPrice(flightInfo));

        flightInfo.setPrice(876);
        assertEquals(1011.52, CalculateAirTicketPrice.ticketPrice(flightInfo));
    }

    // Testing for invalid data

    public void testInvalidPrice() {

        flightInfo = new Flight(-400, 0, 1, 1);
        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testInvalidDataAdults() {

        flightInfo = new Flight(270, -1, 1, 1);
        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testInvalidDataChild() {

        flightInfo = new Flight(600, 0, -25, 1);

        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testInvalidDataBags() {

        flightInfo = new Flight(490, 1, 1, -11);
        try {
            CalculateAirTicketPrice.ticketPrice(flightInfo);
            fail("IAE expected");
        } catch (IllegalArgumentException iae) {
        }
    }

}
