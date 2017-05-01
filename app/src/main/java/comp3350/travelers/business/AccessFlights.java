package comp3350.travelers.business;


import java.util.ArrayList;
import java.util.List;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.objects.Flight;
import comp3350.travelers.persistence.Database;

public class AccessFlights {
    private Database dataAccess;
    private List<Flight> flights;
    private Flight flight;
    private int currentFlight;
    private static String dbName = Main.dbName;

    public AccessFlights() {
        dataAccess = Services.getDataAccess(Main.dbName);
        flights = null;
        flight = null;
        currentFlight = 0;
    }

    public String getFlights(List<Flight> flights) {
        if (flights != null)
            flights.clear();

        return dataAccess.getFlightSequential(flights);
    }

    public Flight getSequential() {
        String result = null;
        Boolean errorOccur = false;
        if (flights == null) {
            flights = new ArrayList<Flight>();
            result = dataAccess.getFlightSequential(flights);
            if("Can't Find Flights Table".equals(result) || "Can't Find Certain Field(s)".equals(result)) {
                flight = null;
                errorOccur = true;
            }
            else
                currentFlight = 0;
        }
        if(!errorOccur){
            if (currentFlight < flights.size()) {
                flight = flights.get(currentFlight);
                currentFlight++;
            } else {
                flights = null;
                flight = null;
                currentFlight = 0;
            }
        }
        else{
            flights = null;
            flight = null;
            currentFlight = 0;
            System.out.println("An error with database");
        }
        return flight;
    }
}
