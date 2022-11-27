package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.customer.Customer;
import com.example.myapplication.customer.CustomerSignin;
import com.example.myapplication.retrofit.CustomerApi;
import com.example.myapplication.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        signUpButton();
        login();
    }
    void signUpButton(){
        MaterialButton signupButton =findViewById( R.id.signup_transfer_button);

        signupButton.setOnClickListener(v ->{
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
    void login() {
        TextInputEditText email = findViewById(R.id.text_email_address_login);
        TextInputEditText password = findViewById(R.id.text_password_login);
        MaterialButton button = findViewById(R.id.signin_button);


        CustomerApi customerApi = RetrofitService
                .getInstance()
                .getRetrofit()
                .create(CustomerApi.class);

        button.setOnClickListener(v -> {
            CustomerSignin customerSignin = new CustomerSignin(
                    Objects.requireNonNull(email.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString());

            customerApi.login(customerSignin)
                    .enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                            System.out.println(t.getMessage());
                            System.out.println(Arrays.toString(t.getStackTrace()));
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}