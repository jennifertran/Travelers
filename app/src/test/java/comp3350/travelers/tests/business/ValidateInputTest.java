package comp3350.travelers.tests.business;

import junit.framework.TestCase;

import comp3350.travelers.application.Main;
import comp3350.travelers.application.Services;
import comp3350.travelers.business.ValidateInput;
import comp3350.travelers.tests.persistence.DataBaseStub;

// Goal test all the methods in the ValidateInput file
public class ValidateInputTest extends TestCase {
    private static String dbName = Main.dbName;
    private ValidateInput validate;

    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(new DataBaseStub(dbName));
        validate = new ValidateInput();
    }

    public void tearDown() {
        Services.closeDataAccess();
    }

    //----------Testing inCities ----------//

    public void testNullCity() {
        assertEquals(false, validate.inCities(null));
    }

    public void testEmptyCity() {
        assertEquals(false, validate.inCities(""));
    }

    public void testNotInCities() {
        assertEquals(false, validate.inCities("London"));
        assertEquals(false, validate.inCities("Sydney"));
        assertEquals(false, validate.inCities("Paris"));
    }

    public void testInCities() {
        assertEquals(true, validate.inCities("Toronto"));
        assertEquals(true, validate.inCities("Winnipeg"));
        assertEquals(true, validate.inCities("Vancouver"));
    }

    //----------Testing checkFrom ----------//

    public void testFromNullParameters() {
        try {
            validate.checkFrom("Winnipeg", null);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkFrom(null, "Winnipeg");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkFrom(null, null);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyFromCity() {
        assertEquals("Please enter origin", validate.checkFrom("", "Winnipeg"));
    }

    public void testSameFromAndToCities() {
        assertEquals("Origin city must not be the same as destination", validate.checkFrom("Winnipeg", "Winnipeg"));
        assertEquals("Origin city must not be the same as destination", validate.checkFrom("Toronto", "Toronto"));
        assertEquals("Origin city must not be the same as destination", validate.checkFrom("Vancouver", "Vancouver"));
    }

    public void testInvalidFromCity() {
        assertEquals("Origin city is not available", validate.checkFrom("London", "Winnipeg"));
        assertEquals("Origin city is not available", validate.checkFrom("Sydney", "Winnipeg"));
        assertEquals("Origin city is not available", validate.checkFrom("Paris", "Winnipeg"));
    }

    public void testValidFromCity() {
        assertNull(validate.checkFrom("Toronto", "Winnipeg"));
        assertNull(validate.checkFrom("Vancouver", "Winnipeg"));
        assertNull(validate.checkFrom("Edmonton", "Winnipeg"));
    }

    //----------Testing checkTo ----------//

    public void testToNullParameters() {
        try {
            validate.checkTo("Toronto", null);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkTo(null, "Toronto");
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkTo(null, null);
            fail("NPE expected");
        } catch (NullPointerException npe) {
        }

    }

    public void testEmptyToCity() {
        assertEquals("Please enter destination", validate.checkTo("", "Winnipeg"));
    }

    public void testSameToAndFromCities() {
        assertEquals("Destination city must not be the same as origin", validate.checkTo("Winnipeg", "Winnipeg"));
        assertEquals("Destination city must not be the same as origin", validate.checkTo("Toronto", "Toronto"));
        assertEquals("Destination city must not be the same as origin", validate.checkTo("Vancouver", "Vancouver"));
    }

    public void testInvalidToCity() {
        assertEquals("Destination city is not available", validate.checkTo("London", "Winnipeg"));
        assertEquals("Destination city is not available", validate.checkTo("Sydney", "Winnipeg"));
        assertEquals("Destination city is not available", validate.checkTo("Paris", "Winnipeg"));
    }

    public void testValidToCity() {
        assertNull(validate.checkTo("Toronto", "Winnipeg"));
        assertNull(validate.checkTo("Vancouver", "Winnipeg"));
        assertNull(validate.checkTo("Edmonton", "Winnipeg"));
    }

    //----------Testing checkDate ----------//

    public void testNullDate() {
        try {
            validate.checkDate(null);
            fail("NPE expected");
        } catch (NullPointerException npe) {

        }
    }

    public void testEmptyDate() {
        assertEquals("Please enter a date", validate.checkDate(""));
    }

    public void testPastDate() {
        assertEquals("Date cannot be from the past", validate.checkDate("03/21/2016"));
        assertEquals("Date cannot be from the past", validate.checkDate("01/01/2017"));
        assertEquals("Date cannot be from the past", validate.checkDate("01/31/1999"));
    }

    public void testValidDate() {
        assertNull(validate.checkDate("12/25/2017"));
        assertNull(validate.checkDate("10/31/2018"));
        assertNull(validate.checkDate("11/11/2018"));
    }

    public void testInvalidDates() {
        assertEquals("Please enter a valid date", validate.checkDate("ABCDEFGHIJKLMNOP"));
        assertEquals("Please enter a valid date", validate.checkDate("////////"));
        assertEquals("Please enter a valid date", validate.checkDate("0S/2H/20BA"));
        assertEquals("Please enter a valid date", validate.checkDate("213/849/20134"));
        assertEquals("Please enter a valid date", validate.checkDate("20/20/2017"));
        assertEquals("Please enter a valid date", validate.checkDate("2222222222222222"));
    }

    public void testFutureDate() {
        assertEquals("Service is not available on that date", validate.checkDate("12/25/2020"));
        assertEquals("Service is not available on that date", validate.checkDate("12/16/2019"));
        assertEquals("Service is not available on that date", validate.checkDate("12/31/2018"));
    }

    //----------Testing checkAdults ----------//
    // checkAdults validates that the user entered valid number of adults

    public void testNullAdults() {
        try {
            validate.checkAdults(null);
        } catch (NullPointerException npe) {

        }

    }

    public void testEmptyAdults() {
        assertEquals("Please enter number of Adults", validate.checkAdults(""));
    }

    public void testMinimumAdults() {
        assertEquals("Minimum 1 Adult required", validate.checkAdults("0"));
    }

    public void testNegAdults() {
        assertEquals("Adults can't be a negative number", validate.checkAdults("-100"));
        assertEquals("Adults can't be a negative number", validate.checkAdults("-256"));
        assertEquals("Adults can't be a negative number", validate.checkAdults("-512"));
    }

    public void testOverLimitAdults() {
        assertEquals("Maximum of 50 Adults allowed", validate.checkAdults("75"));
        assertEquals("Maximum of 50 Adults allowed", validate.checkAdults("190"));
        assertEquals("Maximum of 50 Adults allowed", validate.checkAdults("256"));
    }

    public void testInvalidAdults() {
        assertEquals("Number should be within the range of 1 to 50",
                validate.checkAdults("22222222222222222222"));
        assertEquals("Number should be within the range of 1 to 50",
                validate.checkAdults("-22222222222222222222"));
    }

    public void testValidAdults() {
        assertNull(validate.checkAdults("1"));
        assertNull(validate.checkAdults("25"));
        assertNull(validate.checkAdults("50"));
    }

    //----------Testing checkChildren ----------//

    public void testNullChildren() {

        try {
            validate.checkChildren("5", null);
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkChildren(null, "1");
        } catch (NullPointerException npe) {
        }

        try {
            validate.checkChildren(null, null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyChildren() {
        assertNull(validate.checkChildren("", "1"));
    }

    public void testChildrenHaveAdult() {
        assertEquals("At least 1 Adult required to accompany children", validate.checkChildren("5", "0"));
        assertEquals("At least 1 Adult required to accompany children", validate.checkChildren("1", "0"));
        assertEquals("At least 1 Adult required to accompany children", validate.checkChildren("2", "0"));
    }

    public void testOverLimitChildren() {
        assertEquals("Maximum of 5 Children allowed", validate.checkChildren("15", "1"));
        assertEquals("Maximum of 5 Children allowed", validate.checkChildren("100", "5"));
        assertEquals("Maximum of 5 Children allowed", validate.checkChildren("50", "9"));

    }

    public void testValidChildren() {
        assertNull(validate.checkChildren("2", "1"));
        assertNull(validate.checkChildren("5", "5"));
        assertNull(validate.checkChildren("3", "3"));
        assertNull(validate.checkChildren("0", "1"));
    }

    public void testInvalidChildren() {
        assertEquals("Please enter a valid number from 0-5", validate.checkChildren("22222", "1"));
        assertEquals("Please enter a valid number from 0-5", validate.checkChildren("222222222222222222", "1"));
    }

    public void testNoAdults() {
        assertEquals("At least 1 Adult is required to accompany children", validate.checkChildren("2", ""));
    }

    //----------Testing checkBags ----------//
    public void testNullBags() {
        try {
            validate.checkBags(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyBags() {
        assertNull(validate.checkBags(""));
    }

    public void testOverLimitBags() {
        assertEquals("Maximum of 3 Bags allowed", validate.checkBags("10"));
        assertEquals("Maximum of 3 Bags allowed", validate.checkBags("50"));
        assertEquals("Maximum of 3 Bags allowed", validate.checkBags("100"));
    }

    public void testNegBags() {
        assertEquals("Bags can't be a negative number", validate.checkBags("-100"));
        assertEquals("Bags can't be a negative number", validate.checkBags("-50"));
        assertEquals("Bags can't be a negative number", validate.checkBags("-254"));
    }

    public void testValidBags() {
        assertNull(validate.checkBags("0"));
        assertNull(validate.checkBags("1"));
        assertNull(validate.checkBags("2"));
        assertNull(validate.checkBags("3"));
    }

    public void testInvalidBags() {
        assertEquals("Please enter a valid number from 0-3", validate.checkBags("2222222222222222222"));
        assertEquals("Please enter a valid number from 0-3", validate.checkBags("1234567"));
    }

    //----------Testing checkEmail ----------//

    public void testNullEmail() {
        try {
            validate.checkEmail(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyEmail() {
        assertEquals("Please enter an email", validate.checkEmail(""));
    }

    public void testInvalidEmail() {
        assertEquals("Please enter a valid email", validate.checkEmail("JohnDoegmail.com"));
        assertEquals("Please enter a valid email", validate.checkEmail("12345 ABCDEFG"));
        assertEquals("Please enter a valid email", validate.checkEmail("JohnDoe@.ca"));
    }

    public void testValidEmail() {
        assertNull(validate.checkEmail("JohnDoe@gmail.com"));
        assertNull(validate.checkEmail("JaneSmith@hotmail.com"));
        assertNull(validate.checkEmail("BobbyRose@yahoo.com"));
    }

    //----------Testing noNumbers ----------//

    public void testNullNoNumber() {
        try {
            validate.noNumbers(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyNoNumber() {
        assertEquals("Please enter a name", validate.noNumbers(""));
    }

    public void testInvalidNoNumber() {
        assertEquals("Please enter a valid name", validate.noNumbers("33443"));
        assertEquals("Please enter a valid name", validate.noNumbers("ABC123"));
        assertEquals("Please enter a valid name", validate.noNumbers("22222222222222222"));
    }

    public void testValidNoNumber() {
        assertNull(validate.noNumbers("John"));
        assertNull(validate.noNumbers("Smith"));
        assertNull(validate.noNumbers("Doe"));
    }

    //----------Testing checkPhone ----------//

    public void testNullPhone() {
        try {
            validate.checkPhone(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyPhone() {
        assertEquals("Please enter a phone number", validate.checkPhone(""));
    }

    public void testInvalidPhone() {
        assertEquals("Please enter a valid phone number", validate.checkPhone("6661234567"));
        assertEquals("Please enter a valid phone number", validate.checkPhone("6636-1234-43567"));
        assertEquals("Please enter a valid phone number", validate.checkPhone("0"));
    }

    public void testValidPhone() {
        assertNull(validate.checkPhone("204-222-2222"));
        assertNull(validate.checkPhone("604-123-4567"));
        assertNull(validate.checkPhone("430-431-7232"));
    }

    //----------Testing checkCardName ----------//

    public void testNullCardName() {
        try {
            validate.checkCardName(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyCardName() {
        assertEquals("Please enter a full name", validate.checkCardName(""));
    }

    public void testInvalidCardName() {
        assertEquals("First and last name are required", validate.checkCardName("123 ABC"));
        assertEquals("Please enter a valid full name", validate.checkCardName("123456"));
        assertEquals("Please enter a valid full name", validate.checkCardName("ABCD1234"));
    }

    public void testValidCardName() {
        assertNull(validate.checkCardName("John Smith Doe"));
        assertNull(validate.checkCardName("John Doe"));
    }

    //----------Testing checkCreditCard ----------//

    public void testNullCreditCard() {
        try {
            validate.checkCreditCard(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyCreditCard() {
        assertEquals("Please enter a card number", validate.checkCreditCard(""));
    }

    public void testInvalidCreditCard() {
        assertEquals("Please enter a valid card number", validate.checkCreditCard("ABCD-EFGH-IJKL"));
        assertEquals("Please enter a valid card number", validate.checkCreditCard("AB2D-E45H-I54L"));
        assertEquals("Please enter a valid card number", validate.checkCreditCard("ABF2D-E45FH-I54LF"));
        assertEquals("Please enter a valid card number", validate.checkCreditCard("323232"));
    }

    public void testValidCreditCard() {
        assertNull(validate.checkCreditCard("5105-1051-0510-5100"));
        assertNull(validate.checkCreditCard("4111-1111-1111-1111"));
        assertNull(validate.checkCreditCard("4012-8888-8888-1881"));
    }

    //----------Testing checkCardType ----------//

    public void testNullCardType() {
        try {
            validate.checkCardType(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyCardType() {
        assertEquals("Please enter a card type", validate.checkCardType(""));
    }

    public void testInvalidCardType() {
        assertEquals("Please enter a valid card type", validate.checkCardType("12345"));
        assertEquals("Please enter a valid card type", validate.checkCardType("ABCDEF"));
        assertEquals("Please enter a valid card type", validate.checkCardType("Discover"));
    }

    public void testValidCardType() {
        assertNull(validate.checkCardType("Visa"));
        assertNull(validate.checkCardType("MasterCard"));
        assertNull(validate.checkCardType("TD"));
    }

    //----------Testing checkExpDate ----------//

    public void testNullExpDate() {
        try {
            validate.checkExpDate(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyExpDate() {
        assertEquals("Please enter an expiry date", validate.checkExpDate(""));
    }

    public void testInvalidExpDate() {
        assertEquals("Please enter a valid expiry date", validate.checkExpDate("20/1999"));
        assertEquals("Please enter a valid expiry date", validate.checkExpDate("ABC123"));
        assertEquals("Please enter a valid expiry date", validate.checkExpDate("03/2016"));
    }

    public void testValidExpDate() {
        assertNull(validate.checkExpDate("04/2018"));
        assertNull(validate.checkExpDate("06/2017"));
        assertNull(validate.checkExpDate("12/2019"));
    }

    //----------Testing checkCVV ----------//

    public void testNullCVV() {
        try {
            validate.checkCVV(null);
        } catch (NullPointerException npe) {
        }
    }

    public void testEmptyCVV() {
        assertEquals("Please enter a CVV", validate.checkCVV(""));
    }

    public void testInvalidCVV() {
        assertEquals("Please enter a valid CVV", validate.checkCVV("ABC"));
        assertEquals("Please enter a valid CVV", validate.checkCVV("A6B"));
        assertEquals("Please enter a valid CVV", validate.checkCVV("9KNA"));
    }

    public void testValidCVV() {
        assertNull(validate.checkCVV("032"));
        assertNull(validate.checkCVV("332"));
        assertNull(validate.checkCVV("122"));
    }
}
