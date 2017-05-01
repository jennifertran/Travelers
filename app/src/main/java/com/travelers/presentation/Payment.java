package com.travelers.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.travelers.R;
import com.travelers.business.ValidateInput;

public class Payment extends AppCompatActivity {

    private ValidateInput test;

    String[] creditTypes = {"Visa", "MasterCard", "CIBC", "BMO", "President’s Choice",
            "Scotiabank", "TD", "Capital One", "President’s Choice", "American Express"};

    AutoCompleteTextView cardType;
    EditText firstName;
    EditText lastName;
    EditText emailForm;
    EditText phoneForm;
    EditText infoForm;
    EditText expForm;
    EditText cvvForm;
    EditText nameForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        test = new ValidateInput();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardType = (AutoCompleteTextView) findViewById(R.id.typeForm);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, creditTypes);

        cardType.setAdapter(adapter);
        cardType.setThreshold(1);

    }

    public void confirmOnClick(View v) {

        Intent thankIntent = new Intent(Payment.this, ThankYou.class);

        // Select all the form views
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        emailForm = (EditText) findViewById(R.id.emailForm);
        phoneForm = (EditText) findViewById(R.id.phoneForm);

        cardType = (AutoCompleteTextView) findViewById(R.id.typeForm);
        infoForm = (EditText) findViewById(R.id.infoForm);
        expForm = (EditText) findViewById(R.id.expForm);
        cvvForm = (EditText) findViewById(R.id.cvvForm);
        nameForm = (EditText) findViewById(R.id.nameForm);

        // Get the user's input
        String firstN = firstName.getText().toString();
        String lastN = lastName.getText().toString();
        String email = emailForm.getText().toString();
        String phone = phoneForm.getText().toString();

        String cardName = cardType.getText().toString();
        String number = infoForm.getText().toString();
        String expiry = expForm.getText().toString();
        String cvv = cvvForm.getText().toString();
        String nameCard = nameForm.getText().toString();

        // Reset previous check marks settings if any
        firstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        lastName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        emailForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        phoneForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        cardType.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        infoForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        expForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        cvvForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        nameForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        String errMsg = "";
        int isValid = 0; // Counts the number of forms passing

        // Check if all the user's input have proper input
        // If not, show an error message

        errMsg = test.noNumbers(firstN);
        if (errMsg != null) {
            firstName.setError(errMsg);
        } else {
            firstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.noNumbers(lastN);
        if (errMsg != null) {
            lastName.setError(errMsg);
        } else {
            lastName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkEmail(email);
        if (errMsg != null) {
            emailForm.setError(errMsg);
        } else {
            emailForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkPhone(phone);
        if (errMsg != null) {
            phoneForm.setError(errMsg);
        } else {
            phoneForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkCardType(cardName);
        if (errMsg != null) {
            cardType.setError(errMsg);
        } else {
            cardType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkCreditCard(number);
        if (errMsg != null) {
            infoForm.setError(errMsg);
        } else {
            infoForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkExpDate(expiry);
        if (errMsg != null) {
            expForm.setError(errMsg);
        } else {
            expForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkCVV(cvv);
        if (errMsg != null) {
            cvvForm.setError(errMsg);
        } else {
            cvvForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        errMsg = test.checkCardName(nameCard);
        if (errMsg != null) {
            nameForm.setError(errMsg);
        } else {
            nameForm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.checkmark, 0);
            isValid++;
        }

        if (isValid == 9) {
            Payment.this.startActivity(thankIntent);
        }
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
