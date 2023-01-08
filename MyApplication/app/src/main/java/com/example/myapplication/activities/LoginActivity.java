package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.apis.LoginApi;
import com.example.myapplication.models.user.LoginRequest;
import com.example.myapplication.models.user.UserDetails;
import com.example.myapplication.network.AuthManager;
import com.example.myapplication.network.RetrofitManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    AuthManager authManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // set the content view to the activity_login layout
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
       authManager= AuthManager.getInstance(this);
        signUpButton();
        login();
    }


    void signUpButton() {
        MaterialButton signupButton = findViewById(R.id.signup_transfer_button);

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    void login() {
        TextInputEditText email = findViewById(R.id.text_email_address_login);
        TextInputEditText password = findViewById(R.id.text_password_login);
        MaterialButton signInAsCustomer = findViewById(R.id.signin_customer_button);
        MaterialButton signInAsCompany = findViewById(R.id.signin_company_button);


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        signInAsCustomer.setOnClickListener(v -> {
            LoginRequest loginRequest = new LoginRequest(
                    Objects.requireNonNull(email.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString(),
                    false
            );
            LoginApi loginApi = RetrofitManager.getInstance(httpClient.build()).getRetrofit().create(LoginApi.class);
            Call<UserDetails> call = loginApi.authenticate(loginRequest);
            authenticateCallBack(call, false);
        });

        signInAsCompany.setOnClickListener(v -> {
            LoginRequest loginRequest = new LoginRequest(
                    Objects.requireNonNull(email.getText()).toString(),
                    Objects.requireNonNull(password.getText()).toString(),
                    true
            );
            LoginApi loginApi = RetrofitManager.getInstance(httpClient.build()).getRetrofit().create(LoginApi.class);
            Call<UserDetails> call = loginApi.authenticate(loginRequest);
            authenticateCallBack(call, true);
        });
    }

    private void authenticateCallBack(Call<UserDetails> call, boolean isCompany) {
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<UserDetails> call, @NonNull Response<UserDetails> response) {
                if (response.isSuccessful()) {
                    UserDetails userDetails = response.body();
                    assert userDetails != null;
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", userDetails.getAccessToken());
                    editor.putString("email", userDetails.getEmail());
                    editor.putString("fullName", userDetails.getFullName());
                    editor.putLong("id", userDetails.getId());
                    editor.putString("access_token", userDetails.getAccessToken());
                    editor.putString("refresh_token", userDetails.getAccessToken());

                    editor.apply();
                    if (authManager!= null){
                        authManager.setJwtToken(userDetails.getAccessToken());
                    }
                    if (isCompany) {
                        startActivity(new Intent(LoginActivity.this, CompanyActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, CustomerActivity.class));
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDetails> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}