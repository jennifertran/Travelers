package com.travelers.objects;

public class City {
    private String name;
    private String code;
    private String airport;

    public City(String name) {
        this.name = name;
        code = null;
        airport = null;
    }

    public City(String name, String code, String airport) {
        this.name = name;
        this.code = code;
        this.airport = airport;
    }

    public boolean isEqual(City city) {
        Boolean isEqual = false;

        if (city != null && city.getAirport() != null && city.getCode() != null && city.getName() != null) {
            if (city.getAirport().equals(airport) && city.getName().equals(name) && city.getCode().equals(code))
                isEqual = true;
        }

        return isEqual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String toString() {
        return "City Name: " + name +
                ", Airport Code:" + code +
                ", Airport Name:" + airport;
    }
}
