package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.CompanyApi;
import com.example.myapplication.interfaces.CustomerApi;
import com.example.myapplication.models.company.Company;
import com.example.myapplication.models.company.CompanySignIn;
import com.example.myapplication.models.customer.Customer;
import com.example.myapplication.models.customer.CustomerSignin;
import com.example.myapplication.network.RetrofitService;
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
        MaterialButton signInAsCustomer = findViewById(R.id.signin_customer_button);
        MaterialButton signInAsCompany = findViewById(R.id.signin_company_button);




        signInAsCustomer.setOnClickListener(v -> {
            CustomerApi customerApi = RetrofitService
                    .getInstance()
                    .getRetrofit()
                    .create(CustomerApi.class);
            CustomerSignin customerSignin = new CustomerSignin(
                    Objects.requireNonNull(email.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString());

            customerApi.login(customerSignin)
                    .enqueue(new Callback<Customer>() {
                        @Override
                        public void onResponse(@NonNull Call<Customer> call, @NonNull Response<Customer> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                // Extract the details from response to variables
                                Customer customer = response.body();
                                Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                                assert customer != null;
                                intent.putExtra("name", customer.getFirstName());
                                intent.putExtra("email", customer.getEmail());
                                startActivity(intent);
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

        signInAsCompany.setOnClickListener(v -> {
            CompanyApi companyApi = RetrofitService
                    .getInstance()
                    .getRetrofit()
                    .create(CompanyApi.class);
            CompanySignIn companySignIn = new CompanySignIn(
                    Objects.requireNonNull(email.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString());

            companyApi.login(companySignIn)
                    .enqueue(new Callback<com.example.myapplication.models.company.Company>() {
                        @Override
                        public void onResponse(@NonNull Call<Company> call, @NonNull Response<Company> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                                // Extract the details from response to variables
                                Company company = response.body();
                                Intent intent = new Intent(LoginActivity.this, CompanyActivity.class);
                                assert company != null;
                                intent.putExtra("companyId", company.getId());
                                intent.putExtra("companyName", company.getName());
                                intent.putExtra("companyEmail", company.getEmail());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<com.example.myapplication.models.company.Company> call, @NonNull Throwable t) {
                            System.out.println(t.getMessage());
                            System.out.println(Arrays.toString(t.getStackTrace()));
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}