package comp3350.travelers.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import comp3350.travelers.R;
import comp3350.travelers.business.CalculateAirTicketPrice;
import comp3350.travelers.objects.Flight;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle;

        //Get the bundle
        bundle = getIntent().getExtras();

        //Extract the data…
        final String departCity = bundle.getString("flightFrom");
        final String arrivalCity = bundle.getString("flightTo");
        final String departDate = bundle.getString("dateDepart");
        final String nAdults = bundle.getString("nAdults");
        final String nChildren = bundle.getString("nChildren");
        final String nBags = bundle.getString("nBags");
        final String ticketPrice = bundle.getString("ticketPrice");

        // Display it
        TextView textView = (TextView) findViewById(R.id.fromForm);
        textView.setText(departCity);
        TextView textView2 = (TextView) findViewById(R.id.toForm);
        textView2.setText(arrivalCity);
        TextView textView4 = (TextView) findViewById(R.id.departValue);
        textView4.setText(departDate);
        TextView textView5 = (TextView) findViewById(R.id.baggageValue);
        textView5.setText(nBags);
        TextView textView6 = (TextView) findViewById(R.id.adultValue);
        textView6.setText(nAdults);
        TextView textView7 = (TextView) findViewById(R.id.childrenValue);
        textView7.setText(nChildren);

        Flight flightInfo = new Flight(Integer.parseInt(ticketPrice), Integer.parseInt(nAdults),
                Integer.parseInt(nChildren), Integer.parseInt(nBags));

        String price = "$ " + String.valueOf(CalculateAirTicketPrice.ticketPrice(flightInfo));

        TextView textView3 = (TextView) findViewById(R.id.totalValue);
        textView3.setText(price);
    }

    public void confirmOnClick(View v) {
        Intent paymentIntent = new Intent(Summary.this, Payment.class);

        Summary.this.startActivity(paymentIntent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
