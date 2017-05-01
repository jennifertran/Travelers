package com.travelers.persistence;

import java.util.List;

import com.travelers.objects.Flight;
import com.travelers.objects.City;

public interface Database {
    void open(String string);

    void close();

    String getCitySequential(List<City> cityResult);

    String getFlightSequential(List<Flight> flightResult);

}


