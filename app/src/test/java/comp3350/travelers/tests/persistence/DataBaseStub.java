package comp3350.travelers.tests.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.travelers.application.Main;
import comp3350.travelers.objects.Flight;
import comp3350.travelers.objects.City;
import comp3350.travelers.persistence.Database;

public class DataBaseStub implements Database {
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Flight> flights;
    private ArrayList<City> cities;

    public DataBaseStub(String dbName) {
        this.dbName = dbName;
    }

    public DataBaseStub() {
        this(Main.dbName);
    }

    public void open(String dbName) {
        Flight flight;
        City city;

        flights = new ArrayList<Flight>();

        flight = new Flight("02/22/2017", "02/22/2017", "1:00", "3:30", "Air Canada",
                345, "AC 345", "Calgary", "Winnipeg", "2.5hrs");
        flights.add(flight);

        flight = new Flight("03/13/2017", "03/13/2017", "3:00", "5:30", "Air Canada",
                245, "AC 350", "Vancouver", "Edmonton", "2.5hrs");
        flights.add(flight);

        flight = new Flight("03/22/2017", "03/22/2017", "8:00", "14:30", "Air Canada",
                456, "AC 355", "Winnipeg", "Toronto", "6.5hrs");
        flights.add(flight);

        flight = new Flight("03/28/2017", "03/28/2017", "11:00", "15:30", "Air Canada",
                145, "AC 360", "Vancouver", "Toronto", "4.5hrs");
        flights.add(flight);

        flight = new Flight("04/04/2017", "04/04/2017", "11:30", "15:30", "Air Canada",
                643, "AC 365", "Winnipeg", "Montreal", "4hrs");
        flights.add(flight);

        flight = new Flight("05/09/2017", "05/09/2017", "16:00", "18:30", "Air Canada",
                475, "AC 370", "Winnipeg", "Montreal", "2.5hrs");
        flights.add(flight);

        flight = new Flight("05/26/2017", "05/26/2017", "18:30", "21:30", "Air Canada",
                893, "AC 375", "Montreal", "Ottawa", "3.5hrs");
        flights.add(flight);

        flight = new Flight("05/29/2017", "05/29/2017", "22:00", "13:30", "Air Canada",
                789, "AC 380", "Ottawa", "Toronto", "1.5hrs");
        flights.add(flight);

        flight = new Flight("02/25/2017", "02/25/2017", "2:00", "7:00", "WestJet Encore",
                367, "WR 350", "Winnipeg", "Vancouver", "5hrs");
        flights.add(flight);

        flight = new Flight("04/12/2017", "04/12/2017", "13:00", "15:30", "WestJet Encore",
                645, "WR 355", "Montreal", "Vancouver", "2.5hrs");
        flights.add(flight);

        flight = new Flight("03/02/2017", "03/02/2017", "3:00", "6:30", "West Jet",
                315, "WS 459", "Toronto", "Ottawa", "3hrs");
        flights.add(flight);

        flight = new Flight("04/18/2017", "04/18/2017", "15:00", "16:30", "West Jet",
                925, "WS 469", "Toronto", "Edmonton", "1.5hrs");
        flights.add(flight);


        // Autocomplete Cities for the Flight Form List
        cities = new ArrayList<City>();

        city = new City("Calgary", "YYC", "Calgary International Airport");
        cities.add(city);
        city = new City("Edmonton", "YEG", "Edmonton International Airport");
        cities.add(city);
        city = new City("Montreal", "YMX", "Montreal-Mirabel International Airport");
        cities.add(city);
        city = new City("Ottawa", "YOW", "Ottawa Macdonald-Cartier International Airport");
        cities.add(city);
        city = new City("Toronto", "YYZ", "Toronto Pearson International Airport");
        cities.add(city);
        city = new City("Vancouver", "YVR", "Vancouver International Airport");
        cities.add(city);
        city = new City("Winnipeg", "YWG", "Winnipeg James Armstrong Richardson International Airport");
        cities.add(city);


        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    //----------------------------------------------------------------------

    public String getCitySequential(List<City> cityResult) {
        if (cityResult != null) {
            cityResult.addAll(cities);
            return null;
        } else
            return "Passing Empty List";
    }

    public String getFlightSequential(List<Flight> flightResult) {
        if (flightResult != null) {
            flightResult.addAll(flights);
            return null;
        } else
            return "Passing Empty List";
    }


}
