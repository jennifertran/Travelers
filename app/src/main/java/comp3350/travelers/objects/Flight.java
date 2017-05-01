package comp3350.travelers.objects;

public class Flight {
    private String startData;
    private String endDate;
    private String startTime;
    private String endTime;
    private String company;
    private int price;
    private String flightsID;
    private City departCity;
    private City arriveCity;
    private String departCityN;
    private String arriveCityN;
    private String duration;
    private int numOfAdults;
    private int numOfChildren;
    private int numOfBags;

    public Flight() {

        this.startData = null;
        this.endDate = null;
        this.startTime = null;
        this.endTime = null;
        this.company = null;
        this.price = -1;
        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.numOfBags = 0;
    }

    // The constructor taking in city type parameters for depart and arrive city
    public Flight(String startDate, String endDate, String startTime, String endTime, String company, int price,
                  String id, City departCity, City arriveCity, String duration) {
        this.startData = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.company = company;
        this.price = price;
        this.flightsID = id;
        this.departCity = departCity;
        this.arriveCity = arriveCity;
        this.duration = duration;

        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.numOfBags = 0;
    }

    // The constructor taking in String type parameters for depart and arrive city
    public Flight(String startDate, String endDate, String startTime, String endTime, String company, int price,
                  String id, String departCityN, String arriveCityN, String duration) {
        this.startData = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.company = company;
        this.price = price;
        this.flightsID = id;
        this.departCityN = departCityN;
        this.arriveCityN = arriveCityN;
        this.duration = duration;

        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.numOfBags = 0;
    }

    public Flight(String id) {
        this.startData = null;
        this.endDate = null;
        this.startTime = null;
        this.endTime = null;
        this.company = null;
        this.price = -1;
        this.flightsID = id;
        this.departCity = null;
        this.arriveCity = null;
        this.duration = null;
        this.numOfAdults = 0;
        this.numOfChildren = 0;
        this.numOfBags = 0;
    }

    public Flight(int price, int numOfAdults, int numOfChildren, int numOfBags) {
        this.startData = null;
        this.endDate = null;
        this.startTime = null;
        this.endTime = null;
        this.company = null;
        this.price = price;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.numOfBags = numOfBags;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public void setNumOfChild(int numOfChild) {
        this.numOfChildren = numOfChild;
    }

    public int getNumOfBags() {
        return numOfBags;
    }

    public void setNumOfBags(int numOfBags) {
        this.numOfBags = numOfBags;
    }

    public boolean isEqual(Flight flight) {
        Boolean isEqual = false;

        if (flight != null && flight.getStartData() != null && flight.getEndDate() != null &&
                flight.getStartTime() !=  null && flight.getEndTime() != null && flight.getCompany() !=null && flight.getPrice() != -1 &&
                flight.getFlightsID() != null && flight.getDepartCity() != null && flight.getArriveCity() != null &&
                flight.getDuration() != null) {
            if (startData.equals(this.startData) && endDate.equals(this.endDate) && startTime.equals(this.startTime) &&
                    endTime.equals(this.endTime) && company.equals(this.company) && price == this.price &&
                    flight.getFlightsID().equals(flightsID) && flight.getDepartCity().isEqual(departCity)
                    && flight.getArriveCity().isEqual(arriveCity) && flight.getDuration().equals(duration)) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    public String getStartData() {return startData;}

    public String getEndDate() {return endDate;}

    public String getStartTime(){return startTime;}

    public String getEndTime() {return endTime;}

    public String getCompany() {return company;}

    public int getPrice() {return price;}

    public String getFlightsID() {
        return flightsID;
    }

    public City getDepartCity() {
        return departCity;
    }

    public City getArriveCity() {
        return arriveCity;
    }

    public String getDepartCityN() {
        return departCityN;
    }

    public String getArriveCityN() {
        return arriveCityN;
    }

    public String getDuration() {
        return duration;
    }

    public void setStartData(String startData) {this.startData = startData;}

    public void setEndDate(String endDate) {this.endDate = endDate;}

    public void setStartTime(String startTime) {this.startTime = startTime;}

    public void setEndTime(String endTime) {this.endTime = endTime;}

    public void setCompany(String company) {this.company = company;}

    public void setPrice(int price) {this.price = price;}

    public void setFlightsID(String flightsID) {
        this.flightsID = flightsID;
    }

    public void setDepartCity(City departCity) {
        this.departCity = departCity;
    }

    public void setArriveCity(City arriveCity) {
        this.arriveCity = arriveCity;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String toString() {
        return  "Start Data: " + startData +
                ", End Date: " + endDate +
                ", Start Time: " + startTime +
                ", End Time: " + endTime +
                ", Company: " + company +
                ", Price: " + price +
                ", Flight ID: " + flightsID +
                ", Depart City: " + departCityN +
                ", Arrive City: " + arriveCityN +
                ", Duration: " + duration;
    }
}
