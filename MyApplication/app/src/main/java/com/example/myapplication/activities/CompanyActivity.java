package com.example.myapplication.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCompanyBinding;

import java.util.Objects;

public class CompanyActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCompanyBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(
                this,
                R.id.nav_host_fragment_content_company);
        // new - pass the company name and email to the HomeCompanyFragment
        navController.setGraph(R.navigation.nav_graph, getIntent().getExtras());

        // hide actionbar
        Objects.requireNonNull(getSupportActionBar()).hide();
        appBarConfiguration = new AppBarConfiguration
                .Builder(navController.getGraph())
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_company);
        // new - pass the bundle
        navController.setGraph(R.navigation.nav_graph, getIntent().getExtras());
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
