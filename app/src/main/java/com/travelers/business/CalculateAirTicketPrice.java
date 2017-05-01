package com.travelers.business;

import com.travelers.objects.Flight;


public class CalculateAirTicketPrice {

    public static double ticketPrice(Flight flightInfo) {

        final double pricePerBag = 30.00;
        final double goodsAndServiceTax = 0.07; // 7% tax
        final double securityCharge = 10.00;
        double totalPrice;
        double ticketPrice, ticketPriceChild, numOfAdults, numOfChild, numOfBags;

        if (flightInfo != null) {

            ticketPrice = (double) flightInfo.getPrice();
            ticketPriceChild = ticketPrice / 2.;
            numOfAdults = (double) flightInfo.getNumOfAdults();
            numOfChild = (double) flightInfo.getNumOfChildren();
            numOfBags = (double) flightInfo.getNumOfBags();


            if (ticketPrice <= 0 || numOfAdults < 1 || numOfChild < 0 || numOfBags < 0) {
                throw new IllegalArgumentException("invalid data");
            }


            totalPrice = roundDecimalPlaces(((ticketPrice * numOfAdults) + (ticketPriceChild * numOfChild)
                    + (pricePerBag * numOfBags)) * (goodsAndServiceTax + 1) + securityCharge);


        } else {
            throw new NullPointerException("null List");
        }
        return totalPrice;

    }

    // rounds double to 2 decimal places
    private static double roundDecimalPlaces(double price) {
        double newPrice = price * 100;
        newPrice = Math.round(newPrice);
        newPrice = newPrice / 100;
        return newPrice;
    }


}
