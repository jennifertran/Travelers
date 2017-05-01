package comp3350.travelers.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.text.ParseException;
import java.util.ArrayList;

import comp3350.travelers.R;
import comp3350.travelers.business.AccessCities;
import comp3350.travelers.business.ValidateInput;
import comp3350.travelers.objects.City;

public class FlightForm extends AppCompatActivity {

    private AccessCities accessCities;
    private ArrayList<City> cityList;
    private ValidateInput test;

    AutoCompleteTextView fromLocation;
    AutoCompleteTextView toLocation;
    EditText departDate;
    EditText adults;
    EditText children;
    EditText baggage;
    String[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_form);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accessCities = new AccessCities();
        cityList = new ArrayList<>();
        accessCities.getCities(cityList);
        locations = new String[cityList.size()];
        test = new ValidateInput();

        for (int i = 0; i < cityList.size(); i++) {
            locations[i] = cityList.get(i).getName();
        }

        // Find the particular views
        fromLocation = (AutoCompleteTextView) findViewById(R.id.fromForm);
        toLocation = (AutoCompleteTextView) findViewById(R.id.toForm);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locations);

        // Set up the autocomplete on that specific form
        toLocation.setAdapter(adapter1);
        toLocation.setThreshold(1);

        fromLocation.setAdapter(adapter1);
        fromLocation.setThreshold(1);
    }

    // Sends users to next page
    public void confirmOnClick(View v) throws ParseException {

        Intent flightIntent = new Intent(FlightForm.this, FlightList.class);

        // Select all the form views
        fromLocation = (AutoCompleteTextView) findViewById(R.id.fromForm);
        toLocation = (AutoCompleteTextView) findViewById(R.id.toForm);
        departDate = (EditText) findViewById(R.id.deptDate);
        adults = (EditText) findViewById(R.id.numAdults);
        children = (EditText) findViewById(R.id.numChildren);
        baggage = (EditText) findViewById(R.id.numBags);

        // Get the user's input
        String flightFrom = fromLocation.getText().toString();
        String flightTo = toLocation.getText().toString();
        String dateDepart = departDate.getText().toString();
        String nAdults = adults.getText().toString();
        String nChildren = children.getText().toString();
        String nBags = baggage.getText().toString();

        // Reset previous check marks settings
        fromLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        toLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        departDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        adults.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        children.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        baggage.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        String errMsg = "";
        int isValid = 0; // Counts the number of forms passing

        // If all the forms have proper input keep track of it show a check mark
        // otherwise, give an error message based on the input.

        errMsg = test.checkFrom(flightFrom, flightTo);
        if (errMsg != null) {
            fromLocation.setError(errMsg);
        } else {
            fromLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkTo(flightTo, flightFrom);
        if (errMsg != null) {
            toLocation.setError(errMsg);
        } else {
            toLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkDate(dateDepart);
        if (errMsg != null) {
            departDate.setError(errMsg);
        } else {
            departDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkAdults(nAdults);
        if (errMsg != null) {
            adults.setError(errMsg);
        } else {
            adults.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkChildren(nChildren, nAdults);
        if (errMsg != null) {
            children.setError(errMsg);
        } else {
            children.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;

            // If it's empty, make sure there's a 0
            // to indicate it's empty
            if (nChildren.equals("")) {
                nChildren = "0";
            }
        }

        errMsg = test.checkBags(nBags);
        if (errMsg != null) {
            baggage.setError(errMsg);
        } else {
            baggage.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;

            if (nBags.equals("")) {
                nBags = "0";
            }
        }

        // If the validation is true
        if (isValid == 6) {
            //Create the bundle
            Bundle bundle = new Bundle();

            //Add data to bundle
            bundle.putString("flightFrom", flightFrom);
            bundle.putString("flightTo", flightTo);
            bundle.putString("dateDepart", dateDepart);
            bundle.putString("nAdults", nAdults);
            bundle.putString("nChildren", nChildren);
            bundle.putString("nBags", nBags);

            // Add the bundle to the intent
            flightIntent.putExtras(bundle);

            // Start the next activity
            FlightForm.this.startActivity(flightIntent);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Prevents the user from pressing enter
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_ENTER)
        {
            // Ignore the enter key
            return true;
        }

        // Handle all other the keys in the default way
        return super.onKeyDown(keyCode, event);
    }

}

