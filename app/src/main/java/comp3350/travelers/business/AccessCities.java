package comp3350.travelers.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.objects.City;
import comp3350.travelers.persistence.Database;

public class AccessCities {
    private Database dataAccess;
    private List<City> cities;
    private City city;
    private int currentCity;
    private static String dbName = Main.dbName;

    public AccessCities() {
        dataAccess = Services.getDataAccess(Main.dbName);
        cities = null;
        city = null;
        currentCity = 0;
    }

    public String getCities(List<City> cities) {
        if (cities != null)
            cities.clear();

        return dataAccess.getCitySequential(cities);
    }

    public City getSequential() {
        String result = null;
        Boolean errorOccur = false;
        if (cities == null) {
            cities = new ArrayList<City>();
            result = dataAccess.getCitySequential(cities);
            if("Can't Find Cities Table".equals(result) || "Can't Find Certain Field(s)".equals(result)) {
                city = null;
                errorOccur = true;
            }
            else
                currentCity = 0;
        }
        if(!errorOccur){
            if (currentCity < cities.size()) {
                city = cities.get(currentCity);
                currentCity++;
            } else {
                cities = null;
                city = null;
                currentCity = 0;
            }
        }
        else{
            cities = null;
            city = null;
            currentCity = 0;
            System.out.println("An error with database");
        }
        return city;
    }

}
