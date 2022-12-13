package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

public class AddToCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincart);
    }
    public void onImageClick(View view) {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }



}
