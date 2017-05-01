package comp3350.travelers.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.travelers.objects.Flight;
import comp3350.travelers.objects.City;

public interface Database {
    void open(String string);

    void close();

    String getCitySequential(List<City> cityResult);

    String getFlightSequential(List<Flight> flightResult);

}


