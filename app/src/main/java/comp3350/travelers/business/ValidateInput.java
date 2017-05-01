package comp3350.travelers.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import comp3350.travelers.objects.City;

public class ValidateInput {

    private String[] locations;
    private String regex = "\\d+";
    private String[] creditTypes = {"Visa", "MasterCard", "CIBC", "BMO", "President’s Choice",
            "Scotiabank", "TD", "Capital One", "President’s Choice", "American Express"};


    // Constructor
    public ValidateInput() {
        AccessCities accessCities;
        ArrayList<City> cityList;

        accessCities = new AccessCities();
        cityList = new ArrayList<>();
        accessCities.getCities(cityList);
        locations = new String[cityList.size()];

        for (int i = 0; i < cityList.size(); i++) {
            locations[i] = cityList.get(i).getName();
        }
    }

    // Checks to see if the city exists (based on database)
    public boolean inCities(String city) {
        boolean result = false;

        if (city != null) {
            for (int i = 0; i < locations.length && !result; i++) {
                result = locations[i].equalsIgnoreCase(city);
            }
        }

        return result;
    }

    // Check FromForm, returns null if all tests passed
    public String checkFrom(String input, String to) {
        String result = null;

        if (input != null && to != null) {
            // Check if it's empty
            if (input.equals("")) {
                result = "Please enter origin";

            } else if (input.equals(to)) {
                // Check to see if it is the same as destination
                result = "Origin city must not be the same as destination";

            } else if (!(inCities(input))) {
                // If the city is not in database
                result = "Origin city is not available";
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    // Check toForm returns null if all tests passed
    public String checkTo(String input, String from) {
        String result = null;

        if (input != null && from != null) {
            // Check if it's empty
            if (input.equals("")) {
                result = "Please enter destination";

            } else if (input.equals(from)) {
                // Check to see if it is the same as destination
                result = "Destination city must not be the same as origin";

            } else if (!(inCities(input))) {
                // If the city is not in database
                result = "Destination city is not available";
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    // MM/DD/YYYY format
    public String checkDate(String input) {
        String result = "Please enter a valid date";
        boolean isNumber = true;

        if (input.equals("")) {
            result = "Please enter a date";
        } else {
            // Check the length of the input
            if (input.length() == 10) {
                String tokens[] = input.split("/");

                if (tokens.length == 3) {

                    // Check if it contains integers only
                    for (int i = 0; i < tokens.length && isNumber; i++) {
                        if (!(tokens[i].matches(regex))) {
                            isNumber = false;
                        }
                    }

                    // If all the tokens are pure integers check the numbers
                    if (isNumber) {
                        int month = Integer.parseInt(tokens[0]);
                        int day = Integer.parseInt(tokens[1]);
                        int year = Integer.parseInt(tokens[2]);

                        // Available flights are from today to christmas of next year
                        Calendar limitCal = Calendar.getInstance();
                        int maxYear = limitCal.get(Calendar.YEAR) + 1;
                        limitCal.set(maxYear, Calendar.DECEMBER, 26);

                        // Make sure they are not negative & within range
                        if ((month >= 1 && month <= 12) && (day >= 1 && day <= 31) && (year >= 1)) {

                            Date currentDate = new Date();
                            Date dateLimit = limitCal.getTime();

                            Calendar userCal = Calendar.getInstance();
                            userCal.set(year, month - 1, day);
                            Date userDate = userCal.getTime();

                            // A valid date needs to be greater than or
                            // equal to current date to max date
                            if ((userDate.compareTo(dateLimit) == 0)
                                    || userDate.compareTo(currentDate) == 0) {
                                result = null;
                            }

                            if (userDate.compareTo(dateLimit) < 0
                                    && userDate.compareTo(currentDate) > 0) {
                                result = null;
                            }

                            if (userDate.compareTo(currentDate) < 0) {
                                result = "Date cannot be from the past";
                            }

                            if (userDate.compareTo(dateLimit) > 0) {
                                result = "Service is not available on that date";
                            }

                        }
                    }
                }

            }
        }

        return result;
    }

    // Needs to be at least 1 adult
    public String checkAdults(String adults) {
        String result = "Number should be within the range of 1 to 50";
        int numAdults;

        if (adults != null) {
            // Check if it's empty
            if (adults.equals("")) {
                result = "Please enter number of Adults";
            } else {
                // Check if it's within a certain length
                if (adults.length() < 9) {
                    numAdults = Integer.parseInt(adults);

                    if (numAdults == 0) {
                        result = "Minimum 1 Adult required";
                    } else if (numAdults < 1) {
                        result = "Adults can't be a negative number";
                    } else if (numAdults > 50) {
                        result = "Maximum of 50 Adults allowed";
                    } else if (numAdults >= 1 && numAdults <= 50) {
                        result = null;
                    }
                }
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkChildren(String children, String adults) {
        String result = "Please enter a valid number from 0-5";
        int numAdults;
        int numChildren;

        // Make sure there is input
        if (adults.equals("")) {
            result = "At least 1 Adult is required to accompany children";
        } else if (children != null) {
            // Check if it's empty
            if (children.equals("")) {
                result = null;
            } else {
                // Check if the children and adults within a length

                if (children.length() <= 3 && adults.length() <= 3) {
                    numAdults = Integer.parseInt(adults);
                    numChildren = Integer.parseInt(children);

                    // There needs to be at least 1 adult
                    // Max 5 children
                    if (numAdults >= 1) {
                        if (numChildren >= 0 && numChildren <= 5) {
                            result = null;
                        } else {
                            result = "Maximum of 5 Children allowed";
                        }
                    } else {
                        result = "At least 1 Adult required to accompany children";
                    }
                }
            }
        } else {
            throw new NullPointerException("null parameters");
        }

        return result;
    }

    // Max 3 bags
    public String checkBags(String bags) {
        String result = "Please enter a valid number from 0-3";
        int numBags = 0;

        if (bags != null) {
            // Check if it's empty
            if (bags.equals("")) {
                result = null;
            } else {
                // Check if the number is valid
                if (bags.length() < 7) {
                    numBags = Integer.parseInt(bags);

                    if (numBags < 0) {
                        result = "Bags can't be a negative number";
                    } else if (numBags > 3) {
                        result = "Maximum of 3 Bags allowed";
                    } else {
                        result = null;
                    }
                }
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    // Makes sure that the input does not have any numbers
    // Mainly for first name and last name
    public String noNumbers(String input) {
        String result = "Please enter a valid name";

        // Check if it's empty
        if (input.equals("")) {
            result = "Please enter a name";
        } else {
            // Check if it doesn't contain numbers
            if (input.matches("[a-zA-Z]+")) {
                result = null;
            }
        }

        return result;
    }

    public String checkEmail(CharSequence input) {
        String result = "Please enter a valid email";
        Pattern emailPattern = Pattern.compile(
                "[a-zA-Z0-9+._%\\-]{1,256}" +
                        "@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        if (input != null) {
            // Check if it's empty
            if (input.length() == 0) {
                result = "Please enter an email";
            } else if (emailPattern.matcher(input).matches()) {
                result = null;
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkPhone(String input) {
        String result = "Please enter a valid phone number";
        Pattern phonePattern = Pattern.compile("^(1-)?[0-9]{3}-?[0-9]{3}-?[0-9]{4}$");
        if (input != null) {
            if (input.equals("")) {
                result = "Please enter a phone number";
            } else if (phonePattern.matcher(input).matches() && input.length() == 12) {
                result = null;
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkCardName(String input) {
        String result = "Please enter a valid full name";
        boolean isValid = false;

        if (input != null) {
            // Check if it's empty
            if (input.equals("")) {
                result = "Please enter a full name";
            } else {
                // Check if it doesn't contain numbers
                if (!(input.matches(regex))) {
                    String tokens[] = input.split(" ");

                    if (tokens.length == 2 || tokens.length == 3) {
                        // Check if any tokens contains numbers

                        for (int i = 0; i < tokens.length && !isValid; i++) {
                            isValid = tokens[i].matches(regex);
                        }

                        if (!isValid) {
                            result = null;
                        } else {
                            result = "First and last name are required";
                        }
                    }
                }
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    // Check to see if the card type the user inputted is in one of the given suggestions.
    public String checkCardType(String input) {
        String result = "Please enter a valid card type";
        boolean isValid = false;

        if (input != null) {
            if (input.equals("")) {
                result = "Please enter a card type";
            } else {

                for (int i = 0; i < creditTypes.length && !isValid; i++) {
                    isValid = creditTypes[i].equalsIgnoreCase(input);
                }

                if (isValid) {
                    result = null;
                }

            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkCreditCard(String input) {
        String result = "Please enter a valid card number";
        boolean isValid = false;

        if (input != null) {
            if (input.equals("")) {
                result = "Please enter a card number";
            } else {
                if (input.length() == 19) {
                    String[] tokens = input.split("-");

                    if (tokens.length == 4) {
                        for (int i = 0; i < tokens.length && !isValid; i++) {
                            isValid = tokens[i].matches(regex);
                        }

                        if (isValid) {
                            result = null;
                        }
                    }
                }
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkExpDate(String input) {
        String result = "Please enter a valid expiry date";
        int month = 0;
        int year = 0;

        if (input != null) {
            if (input.equals("")) {
                result = "Please enter an expiry date";
            } else {
                if (input.length() == 7) {
                    String[] tokens = input.split("/");

                    if (tokens.length == 2) {

                        // Check if they are integers
                        if (tokens[0].matches(regex) && tokens[1].matches(regex)) {
                            month = Integer.parseInt(tokens[0]);
                            year = Integer.parseInt(tokens[1]);
                            Calendar limitCal = Calendar.getInstance();
                            int maxYear = limitCal.get(Calendar.YEAR) + 2;
                            int currYear = limitCal.get(Calendar.YEAR);

                            // A valid date needs to be greater than or
                            // equal to current date to max date
                            if ((month >= 1 && month <= 12) && (year <= maxYear) && (year >= currYear)) {
                                result = null;
                            }
                        }
                    }
                }
            }

        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

    public String checkCVV(String input) {
        String result = "Please enter a valid CVV";

        if (input != null) {
            if (input.equals("")) {
                result = "Please enter a CVV";
            } else {
                if (input.matches(regex) && input.length() == 3) {
                    result = null;
                }
            }
        } else {
            throw new NullPointerException("null parameter");
        }

        return result;
    }

}
