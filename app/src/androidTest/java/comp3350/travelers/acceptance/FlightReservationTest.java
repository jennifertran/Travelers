package comp3350.travelers.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.travelers.R;
import comp3350.travelers.presentation.MainActivity;

public class FlightReservationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    // EditText views for the Flight information form
    private EditText fromLocation;
    private EditText toLocation;
    private EditText departDate;
    private EditText adults;
    private EditText children;
    private EditText baggage;

    // EditText views for the Payment information form
    private EditText firstName;
    private EditText lastName;
    private EditText emailForm;
    private EditText phoneForm;
    private EditText cardType;
    private EditText infoForm;
    private EditText expForm;
    private EditText cvvForm;
    private EditText nameForm;


    public FlightReservationTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

        solo.waitForActivity("MainActivity");
        solo.clickOnButton(0);
        solo.assertCurrentActivity("Expected activity FlightForm", "FlightForm");

        fromLocation = (EditText) solo.getView(R.id.fromForm);
        toLocation = (EditText) solo.getView(R.id.toForm);
        departDate = (EditText) solo.getView(R.id.deptDate);
        adults = (EditText) solo.getView(R.id.numAdults);
        children = (EditText) solo.getView(R.id.numChildren);
        baggage = (EditText) solo.getView(R.id.numBags);

    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testValidFlightReservation() {
        validFlightFormTest();
        flightListTest();
        flightSummaryTest();
        validFlightPaymentTest();
    }

    // Tests the errors messages that occurs if the user enter
    public void testInvalidFlightReservation() {
        invalidFlightFormTest();
        flightListTest();
        flightSummaryTest();
        invalidFlightPaymentTest();
    }

    public void validFlightFormTest() {
        solo.enterText(fromLocation, "Winnipeg");
        solo.enterText(toLocation, "Toronto");
        solo.enterText(departDate, "05/28/2017");
        solo.enterText(adults, "1");
        solo.enterText(children, "2");
        solo.enterText(baggage, "2");

        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Expected activity FlightList", "FlightList");
    }

    public void invalidFlightFormTest() {

        // From Test
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(fromLocation);
        Assert.assertTrue(solo.searchText("Please enter origin"));

        solo.enterText(fromLocation, "Paris");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(fromLocation);
        Assert.assertTrue(solo.searchText("Origin city is not available"));

        solo.clearEditText(fromLocation);
        solo.enterText(fromLocation, "Winnipeg");
        solo.clickOnButton("CONFIRM");

        // To Test
        solo.clickOnView(toLocation);
        Assert.assertTrue(solo.searchText("Please enter destination"));

        solo.enterText(toLocation, "Sydney");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(toLocation);
        Assert.assertTrue(solo.searchText("Destination city is not available"));

        solo.clearEditText(toLocation);
        solo.enterText(toLocation, "Toronto");
        solo.clickOnButton("CONFIRM");

        // Departure Date Test
        solo.clickOnView(departDate);
        Assert.assertTrue(solo.searchText("Please enter a date"));

        solo.enterText(departDate, "03/09/2009");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(departDate);
        Assert.assertTrue(solo.searchText("Date cannot be from the past"));

        solo.clearEditText(departDate);
        solo.enterText(departDate, "05/28/2017");
        solo.clickOnButton("CONFIRM");

        // Adults Test
        solo.clickOnView(adults);
        Assert.assertTrue(solo.searchText("Please enter number of Adults"));

        solo.enterText(adults, "100");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(adults);
        Assert.assertTrue(solo.searchText("Maximum of 50 Adults allowed"));

        solo.clearEditText(adults);
        solo.enterText(adults, "1");

        // Children Test
        solo.clickOnView(children);
        solo.enterText(children, "10");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(children);
        Assert.assertTrue(solo.searchText("Maximum of 5 Children allowed"));

        solo.clearEditText(children);
        solo.enterText(children, "2");

        // Baggage Test
        solo.clickOnView(baggage);
        solo.enterText(baggage, "10");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(baggage);
        Assert.assertTrue(solo.searchText("Maximum of 3 Bags allowed"));

        solo.clearEditText(baggage);
        solo.enterText(baggage, "2");
        solo.clickOnButton("CONFIRM");
    }


    public void flightListTest() {
        Assert.assertTrue(solo.searchText("May 28, 2017"));
        Assert.assertTrue(solo.searchText("Winnipeg to Toronto"));

        Assert.assertTrue(solo.searchText("AC 345"));
        Assert.assertTrue(solo.searchText("AC 350"));
        Assert.assertTrue(solo.searchText("AC 355"));
        Assert.assertTrue(solo.searchText("AC 360"));

        solo.clickOnButton("LOWEST");
        Assert.assertTrue(solo.searchText("AC 360"));
        Assert.assertTrue(solo.searchText("AC 350"));
        Assert.assertTrue(solo.searchText("WS 459"));
        Assert.assertTrue(solo.searchText("AC 345"));

        solo.clickOnButton("HIGHEST");
        Assert.assertTrue(solo.searchText("WS 469"));
        Assert.assertTrue(solo.searchText("AC 375"));
        Assert.assertTrue(solo.searchText("AC 380"));
        Assert.assertTrue(solo.searchText("WR 355"));

        solo.clickOnText("WS 469");
        solo.clickOnButton("CHECKOUT");
        solo.assertCurrentActivity("Expected activity Summary", "Summary");
    }

    public void flightSummaryTest() {
        Assert.assertTrue(solo.searchText("Winnipeg"));
        Assert.assertTrue(solo.searchText("Toronto"));
        Assert.assertTrue(solo.searchText("05/28/2017"));
        Assert.assertTrue(solo.searchText("2053.7"));
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Expected activity Payment", "Payment");
    }

    public void validFlightPaymentTest() {
        solo.scrollUp();
        getPaymentViews();

        // Customer Info
        solo.enterText(firstName, "Stew");
        solo.enterText(lastName, "Dent");
        solo.enterText(emailForm, "stewDent@uofm.ca");
        solo.enterText(phoneForm, "204-123-4567");

        // Billing Info
        solo.enterText(cardType, "Visa");
        solo.enterText(infoForm, "4321-7890-5678-3402");
        solo.enterText(expForm, "06/2018");
        solo.enterText(cvvForm, "387");
        solo.enterText(nameForm, "Stew Dent");

        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Expected activity ThankYou", "ThankYou");
    }

    public void invalidFlightPaymentTest() {
        getPaymentViews();

        // First Name test
        solo.clickOnView(firstName);
        solo.clickOnButton("CONFIRM");
        Assert.assertTrue(solo.searchText("Please enter a name"));

        solo.enterText(firstName, "Stew Dent");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(firstName);
        Assert.assertTrue(solo.searchText("Please enter a valid name"));

        solo.clearEditText(firstName);
        solo.enterText(firstName, "Stew");
        solo.clickOnButton("CONFIRM");
        // Last Name test
        solo.clickOnView(lastName);
        Assert.assertTrue(solo.searchText("Please enter a name"));

        solo.enterText(lastName, "Dent Stew");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(lastName);
        Assert.assertTrue(solo.searchText("Please enter a valid name"));

        solo.clearEditText(lastName);
        solo.enterText(lastName, "Dent");
        solo.clickOnButton("CONFIRM");

        // Email test
        solo.clickOnView(emailForm);
        Assert.assertTrue(solo.searchText("Please enter an email"));

        solo.enterText(emailForm, "stewDent.gmail");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(emailForm);
        Assert.assertTrue(solo.searchText("Please enter a valid email"));

        solo.clearEditText(emailForm);
        solo.enterText(emailForm, "stewDent@uofm.ca");
        solo.clickOnButton("CONFIRM");

        // Phone Number test
        solo.clickOnView(phoneForm);
        Assert.assertTrue(solo.searchText("Please enter a phone number"));

        solo.enterText(phoneForm, "123-211122");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(phoneForm);
        Assert.assertTrue(solo.searchText("Please enter a valid phone number"));

        solo.clearEditText(phoneForm);
        solo.enterText(phoneForm, "204-123-4567");
        solo.clickOnButton("CONFIRM");

        // Card Type test
        solo.clickOnView(cardType);
        Assert.assertTrue(solo.searchText("Please enter a card type"));

        solo.enterText(cardType, "MasterVisaCard");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(cardType);
        Assert.assertTrue(solo.searchText("Please enter a valid card type"));

        solo.clearEditText(cardType);
        solo.enterText(cardType, "Visa");
        solo.clickOnButton("CONFIRM");

        // Card Number test
        solo.clickOnView(infoForm);
        Assert.assertTrue(solo.searchText("Please enter a card number"));

        solo.enterText(infoForm, "1234-2222-2232");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(infoForm);
        Assert.assertTrue(solo.searchText("Please enter a valid card number"));

        solo.clearEditText(infoForm);
        solo.enterText(infoForm, "4321-7890-5678-3402");
        solo.clickOnButton("CONFIRM");

        // Expiry Date test
        solo.clickOnView(expForm);
        Assert.assertTrue(solo.searchText("Please enter an expiry date"));

        solo.enterText(expForm, "09/2016");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(expForm);
        Assert.assertTrue(solo.searchText("Please enter a valid expiry date"));

        solo.clearEditText(expForm);
        solo.enterText(expForm, "09/2018");
        solo.clickOnButton("CONFIRM");

        // CVV test
        solo.clickOnView(cvvForm);
        Assert.assertTrue(solo.searchText("Please enter a CVV"));

        solo.enterText(cvvForm, "10");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(cvvForm);
        Assert.assertTrue(solo.searchText("Please enter a valid CVV"));

        solo.clearEditText(cvvForm);
        solo.enterText(cvvForm, "387");
        solo.clickOnButton("CONFIRM");

        // Name of card test
        solo.clickOnView(nameForm);
        Assert.assertTrue(solo.searchText("Please enter a full name"));

        solo.enterText(nameForm, "Stew");
        solo.clickOnButton("CONFIRM");
        solo.clickOnView(nameForm);
        Assert.assertTrue(solo.searchText("Please enter a valid full name"));

        solo.clearEditText(nameForm);
        solo.enterText(nameForm, "Stew Dent");
        solo.clickOnButton("CONFIRM");
        solo.assertCurrentActivity("Expected activity ThankYou", "ThankYou");
    }



    public void getPaymentViews(){
        firstName = (EditText) solo.getView(R.id.firstName);
        lastName = (EditText) solo.getView(R.id.lastName);
        emailForm = (EditText) solo.getView(R.id.emailForm);
        phoneForm = (EditText) solo.getView(R.id.phoneForm);
        cardType = (EditText) solo.getView(R.id.typeForm);
        infoForm = (EditText) solo.getView(R.id.infoForm);
        expForm = (EditText) solo.getView(R.id.expForm);
        cvvForm = (EditText) solo.getView(R.id.cvvForm);
        nameForm = (EditText) solo.getView(R.id.nameForm);
    }
}
