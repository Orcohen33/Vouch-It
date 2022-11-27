package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.customer.Customer;
import com.example.myapplication.customer.CustomerSignup;
import com.example.myapplication.retrofit.CustomerApi;
import com.example.myapplication.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup();
    }

    private void signup() {
        TextInputEditText emailInput = findViewById(R.id.text_email_address_signup);
        TextInputEditText fullNameInput = findViewById(R.id.FullNameSignup);
        TextInputEditText passwordInput = findViewById(R.id.text_password_signup);
        TextInputEditText confirmPasswordInput = findViewById(R.id.text_password_confirm_signup);
        MaterialButton button = findViewById(R.id.signup_request_button);

        CustomerApi customerApi = RetrofitService
                .getInstance()
                .getRetrofit()
                .create(CustomerApi.class);

        button.setOnClickListener(v -> {
            String fullName = Objects.requireNonNull(fullNameInput.getText()).toString();
            String email = Objects.requireNonNull(emailInput.getText()).toString();
            String password = Objects.requireNonNull(passwordInput.getText()).toString();
            String confirmPassword = Objects.requireNonNull(confirmPasswordInput.getText()).toString();
            if (!password.trim().equals(confirmPassword.trim())) {
                Toast.makeText(this, "Passwords don't match, please try again", Toast.LENGTH_SHORT).show();
            }
            CustomerSignup customerSignup = new CustomerSignup(
                    fullName,
                    email,
                    password);

            customerApi.signup(customerSignup)
                    .enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Signup success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Customer> call, @NonNull Throwable t) {
                            Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(t.getMessage());
                            System.out.println("TEST");
                        }
                    });
        });


    }
}