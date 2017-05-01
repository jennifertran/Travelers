package comp3350.travelers.business;

import java.util.Comparator;

import comp3350.travelers.objects.Flight;

public class SortTicketPricesLowToHigh implements Comparator<Flight> {
    public int compare(Flight flight1, Flight flight2) {
        return flight1.getPrice() - flight2.getPrice();
    }
}
