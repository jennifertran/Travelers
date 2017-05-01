package comp3350.travelers.business;

import java.util.Comparator;

import comp3350.travelers.objects.Flight;

public class SortTicketPricesHighToLow implements Comparator<Flight> {
    public int compare(Flight flight1, Flight flight2) {
        return flight2.getPrice() - flight1.getPrice();
    }
}
