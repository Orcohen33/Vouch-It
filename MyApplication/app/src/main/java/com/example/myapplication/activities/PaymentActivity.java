package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentpage);
    }
    public void pay(){
        EditText cardNumber = findViewById(R.id.credit_card_number);
        EditText cardHolderName = findViewById(R.id.card_holder_name);
        EditText expiryMonth = findViewById(R.id.expiration_month);
        EditText expiryYear = findViewById(R.id.expiration_year);
        EditText cvv = findViewById(R.id.cvv);
        String cardNumberString = cardNumber.getText().toString();
        String cardHolderNameString = cardHolderName.getText().toString();
        String expiryMonthString = expiryMonth.getText().toString();
        String expiryYearString = expiryYear.getText().toString();
        String cvvString = cvv.getText().toString();
        if (cardNumberString.length() != 16) {
            cardNumber.setError("Invalid card number");
        }
        if (cardHolderNameString.length() == 0) {
            cardHolderName.setError("Invalid card holder name");
        }
        if (expiryMonthString.length() != 2) {
            expiryMonth.setError("Invalid expiry month");
        }
        if (expiryYearString.length() != 4) {
            expiryYear.setError("Invalid expiry year");
        }
        if (cvvString.length() != 3) {
            cvv.setError("Invalid cvv");
        }
//
//        if(cardNumberString.isEmpty() || cardHolderNameString.isEmpty() || expiryYearString.isEmpty() || expiryMonthString.isEmpty() || cvvString.isEmpty()){
//            return;
//        }
//
//        if(Integer.parseInt(expiryMonthString) > 12 || Integer.parseInt(expiryMonthString) < 1){
//            return;
//        }
//        Intent intent = new Intent(this, CustomerActivity.class);
//        startActivity(intent);

    }
    public void onBackPaymentImageClick(View view) {
        Intent intent = new Intent(this, AddToCartActivity.class);
        startActivity(intent);
    }
}

