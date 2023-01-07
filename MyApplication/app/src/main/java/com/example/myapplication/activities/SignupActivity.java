package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.R;
import com.example.myapplication.apis.LoginApi;
import com.example.myapplication.models.user.RegisterRequest;
import com.example.myapplication.models.user.UserDetails;
import com.example.myapplication.network.RetrofitManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import okhttp3.OkHttpClient;
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

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        signupButton.setOnClickListener(v -> {
            String email = Objects.requireNonNull(emailInput.getText()).toString();
            String fullName = Objects.requireNonNull(fullNameInput.getText()).toString();
            String password = Objects.requireNonNull(passwordInput.getText()).toString();
            String confirmPassword = Objects.requireNonNull(confirmPasswordInput.getText()).toString();
            boolean isCompany = signupAsCompanyCheckBox.isChecked();

            if (email.isEmpty() || fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            RegisterRequest registerRequest = new RegisterRequest(fullName, email, password, isCompany);
            System.out.println(registerRequest);
            LoginApi loginApi = RetrofitManager.getInstance(httpClient.build()).createService(LoginApi.class);
            Call<UserDetails> call = loginApi.register(registerRequest);

            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                    if (response.isSuccessful()) {
                        UserDetails userDetails = response.body();
                        Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                        AppBarConfiguration.OnNavigateUpListener onNavigateUpListener = () -> {
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            return true;
                        };
                        onNavigateUpListener.onNavigateUp();
                    } else {
                        Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserDetails> call, Throwable t) {

                }
            });
        });
    }
}

