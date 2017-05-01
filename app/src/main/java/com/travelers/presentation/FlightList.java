package com.travelers.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;

import com.travelers.R;
import com.travelers.business.AccessFlights;
import com.travelers.business.SortTicketPricesHighToLow;
import com.travelers.business.SortTicketPricesLowToHigh;
import com.travelers.objects.Flight;

public class FlightList extends AppCompatActivity {

    private AccessFlights accessFlights;
    private ArrayList<Flight> flightList;
    private ArrayAdapter<Flight> flightArrayAdapter;
    private int selectedFlightPosition = -1;
    private Bundle bundle;
    private Boolean isLowClicked;
    private Boolean isHighClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isLowClicked = false;
        isHighClicked = false;

        accessFlights = new AccessFlights();

        //Get the bundle
        bundle = getIntent().getExtras();

        //Extract the data…
        final String departCity = bundle.getString("flightFrom");
        final String arrivalCity = bundle.getString("flightTo");
        final String departDate = bundle.getString("dateDepart");

        int monthNum = Integer.parseInt(Character.toString(departDate.charAt(0)) + Character.toString(departDate.charAt(1)));
        String month, date, year;
        month = new DateFormatSymbols().getMonths()[monthNum - 1];
        date = ((departDate.charAt(3) == '0') ? "" : Character.toString(departDate.charAt(3))) + Character.toString(departDate.charAt(4));
        year = Character.toString(departDate.charAt(6)) + Character.toString(departDate.charAt(7))
                + Character.toString(departDate.charAt(8)) + Character.toString(departDate.charAt(9));


        TextView textView = (TextView) findViewById(R.id.flightList);
        textView.setText("Flights Options for:\n" + month + " " + date + ", " + year);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        flightList = new ArrayList<Flight>();
        accessFlights.getFlights(flightList);

        flightArrayAdapter = new ArrayAdapter<Flight>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, flightList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(flightList.get(position).getCompany() + "\t\t\t\t\t\t\t" + " Price: $ "
                        + flightList.get(position).getPrice() + "\n"
                        + flightList.get(position).getFlightsID() + "\t\t\t"
                        + departCity + " to " + arrivalCity);
                text1.setTextSize((float) 25);
                text1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                text2.setText("Departure :" + flightList.get(position).getStartTime()
                        + "\t\t\t\tArrival: " + flightList.get(position).getEndTime());
                //getFlightsID or any get method should have string as return value

                text2.setTextSize((float) 25);
                text2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                return view;
            }
        };

        final ListView listView = (ListView) findViewById(R.id.listFlights);
        listView.setAdapter(flightArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Button checkoutButton = (Button) findViewById(R.id.Checkout);

                if (position == selectedFlightPosition) {
                    listView.setItemChecked(position, false);
                    checkoutButton.setEnabled(false);
                    selectedFlightPosition = -1;
                } else {
                    listView.setItemChecked(position, true);
                    checkoutButton.setEnabled(true);
                    selectedFlightPosition = position;
                }
            }
        });

        final Button buttonHigh = (Button) findViewById(R.id.sortAscending);
        final Button buttonLow = (Button) findViewById(R.id.sortDescending);

        buttonHigh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                    buttonLow.setPressed(false);

                    Collections.sort(flightList, new SortTicketPricesLowToHigh()); // Lowest to Highest
                    final ListView listView = (ListView) findViewById(R.id.listFlights);
                    listView.setAdapter(flightArrayAdapter);

                }

                return true; //Return true, so there will be no onClick-event
            }
        });

        buttonLow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                    buttonHigh.setPressed(false);

                    Collections.sort(flightList, new SortTicketPricesHighToLow()); // Highest to Lowest
                    final ListView listView = (ListView) findViewById(R.id.listFlights);
                    listView.setAdapter(flightArrayAdapter);

                }

                return true; //Return true, so there will be no onClick-event
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flights, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void confirmOnClick(View v) {
        Intent flightIntent = new Intent(FlightList.this, Summary.class);

        // Sending the ticketPrice of the flight the user choose
        bundle.putString("ticketPrice", String.valueOf(flightList.get(selectedFlightPosition).getPrice()));

        // Add the bundle to the intent
        flightIntent.putExtras(bundle);

        FlightList.this.startActivity(flightIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
