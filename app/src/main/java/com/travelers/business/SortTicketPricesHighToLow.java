package com.travelers.business;

import java.util.Comparator;

import com.travelers.objects.Flight;

public class SortTicketPricesHighToLow implements Comparator<Flight> {
    public int compare(Flight flight1, Flight flight2) {
        return flight2.getPrice() - flight1.getPrice();
    }
}
