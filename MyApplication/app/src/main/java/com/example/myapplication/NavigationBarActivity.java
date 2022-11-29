package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NavigationBarActivity extends AppCompatActivity {

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.menu_image_view).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
            System.out.println("Menu clicked");
        });


    }

    private void searchBar(){
        TextInputEditText searchEditText = findViewById(R.id.search_bar);
        searchEditText.setOnClickListener(v -> {
            String textInput = Objects.requireNonNull(searchEditText.getText()).toString();
            /*
            TODO: after creating a database - we need to check here if the input match a data in the db,
            if inside - to show it on the screen
             */


        });
    }

}