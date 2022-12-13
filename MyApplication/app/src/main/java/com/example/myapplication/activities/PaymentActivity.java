package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentpage);
        pay();

    }
    public void pay(){
        EditText cardNumber = findViewById(R.id.credit_card_number);
        EditText cardHolderName = findViewById(R.id.card_holder_name);
        EditText expiryMonth = findViewById(R.id.expiration_month);
        EditText expiryYear = findViewById(R.id.expiration_year);
        EditText cvv = findViewById(R.id.cvv);

        // Check validation of the fields
        EditText.OnFocusChangeListener onFocusChangeListenerCardNumber = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().length() != 16) {
                    editText.setError("Credit card number must be 16 digits");
                }
            }
        };
        cardNumber.setOnFocusChangeListener(onFocusChangeListenerCardNumber);

        EditText.OnFocusChangeListener onFocusChangeListenerCardHolderName = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().length() < 3) {
                    editText.setError("Card holder name must be at least 3 characters");
                }
            }
        };
        cardHolderName.setOnFocusChangeListener(onFocusChangeListenerCardHolderName);

        View.OnFocusChangeListener onFocusChangeListenerExpiryMonth = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().toString().isEmpty() || (editText.getText().length() != 2 ||
                        (Integer.parseInt(editText.getText().toString()) > 12 ||
                        Integer.parseInt(editText.getText().toString()) < 1))) {
                    editText.setError("- Expiry month must be 2 digits\n- Expiry month must be between 1 and 12");
                }
            }
        };
        expiryMonth.setOnFocusChangeListener(onFocusChangeListenerExpiryMonth);

        View.OnFocusChangeListener onFocusChangeListenerExpiryYear = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().toString().isEmpty() ||
                        editText.getText().length() != 4 || Integer.parseInt(editText.getText().toString()) < 2021) {
                    editText.setError("- Expiry year must be 4 digits\n- Expiry year must be at least 2021");
                }
            }
        };
        expiryYear.setOnFocusChangeListener(onFocusChangeListenerExpiryYear);

        View.OnFocusChangeListener onFocusChangeListenerCvv = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().length() != 3) {
                    editText.setError("CVV must be 3 digits");
                }
            }
        };
        cvv.setOnFocusChangeListener(onFocusChangeListenerCvv);
    }
    public void onBackPaymentImageClick(View view) {
        Intent intent = new Intent(this, AddToCartActivity.class);
        startActivity(intent);
    }
}

