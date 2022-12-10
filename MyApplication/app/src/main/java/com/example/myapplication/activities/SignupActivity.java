package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.CompanyApi;
import com.example.myapplication.models.company.Company;
import com.example.myapplication.models.company.CompanySignup;
import com.example.myapplication.models.customer.Customer;
import com.example.myapplication.models.customer.CustomerSignup;
import com.example.myapplication.interfaces.CustomerApi;
import com.example.myapplication.network.RetrofitService;
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
        CheckBox signupAsCompanyCheckBox = findViewById(R.id.company_signup_checkbox);
        MaterialButton signupButton = findViewById(R.id.signup_request_button);


        signupButton.setOnClickListener(v -> {
            String fullName = Objects.requireNonNull(fullNameInput.getText()).toString();
            String email = Objects.requireNonNull(emailInput.getText()).toString();
            String password = Objects.requireNonNull(passwordInput.getText()).toString();
            String confirmPassword = Objects.requireNonNull(confirmPasswordInput.getText()).toString();
            if (!password.trim().equals(confirmPassword.trim())) {
                Toast.makeText(this, "Passwords don't match, please try again", Toast.LENGTH_SHORT).show();
            }
            if (!signupAsCompanyCheckBox.isChecked()) {
                CustomerSignup customerSignup = new CustomerSignup(fullName, email, password);
                CustomerApi customerApi = RetrofitService.getInstance().getRetrofit().create(CustomerApi.class);
                customerApi.signup(customerSignup).enqueue(new Callback<Customer>() {
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
                    }
                });
            }
            else{
                CompanySignup companySignup = new CompanySignup(fullName, email, password);
                CompanyApi companyApi = RetrofitService.getInstance().getRetrofit().create(CompanyApi.class);
                companyApi.signup(companySignup)
                        .enqueue(new Callback<Company>() {
                    @Override
                    public void onResponse(@NonNull Call<Company> call, @NonNull Response<Company> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Signup success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Company> call, @NonNull Throwable t) {
                        Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });
            }
        });


    }
}