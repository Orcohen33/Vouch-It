package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCompanyBinding;
import com.example.myapplication.fragments.company.analysis.AnalysisFragment;
import com.example.myapplication.fragments.company.home.HomeCompanyFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompanyActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.myapplication.databinding.ActivityCompanyBinding binding = ActivityCompanyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // the nav controller is used to navigate between fragments
        NavController navController = Navigation.findNavController(
                this,
                R.id.nav_host_fragment_content_company);
        // new - pass the company name and email to the HomeCompanyFragment
        navController.setGraph(R.navigation.company_nav_graph, getIntent().getExtras());

        // the bottom navigation view is used to navigate between fragments
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        // the app bar configuration is used to set the title of the action bar
        NavigationUI.setupWithNavController(bottomNav, navController);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_company);
        assert navHostFragment != null;


        // Listener for Bottom Navigation View item selections.
        // This function will handle switching between fragments when a bottom navigation item is selected.
        // It will also remove all previous fragments from the backstack to ensure the user is taken to the selected fragment.
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
            switch (item.getItemId()) {
                case R.id.home:
                    if (!(currentFragment instanceof HomeCompanyFragment)) {
                        navController.navigate(R.id.HomeCompanyFragment,
                                null,
                                new NavOptions
                                        .Builder()
                                        .setPopUpTo(R.id.HomeCompanyFragment, true)
                                        .setLaunchSingleTop(false)
                                        .build()
                        );
                    }
                    break;
                case R.id.analysis:
                    if (!(currentFragment instanceof AnalysisFragment)) {
                        navController.popBackStack(R.id.analysisFragment, false, true);
                        navController.navigate(R.id.analysisFragment,
                                null,
                                new NavOptions
                                        .Builder()
                                        .setPopUpTo(R.id.analysisFragment, true)
                                        .setLaunchSingleTop(false)
                                        .build());
                    }
                    break;
            }
            return true;
        });

        // * Listener for Floating Action Button click.
        // * This function will handle navigating to the AddCouponFragment when the Floating Action Button is clicked.
        // * It will also pass a bundle containing "isEdit" set to false to the AddCouponFragment.
        // * depending on which fragment is currently displayed, it will navigate to the AddCouponFragment from that fragment.
        FloatingActionButton fab = findViewById(R.id.floating_button_company);
        fab.setOnClickListener(view -> {
            Fragment currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isEdit", false);
            if (currentFragment instanceof HomeCompanyFragment) {
                navController.navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment, bundle);
            } else if (currentFragment instanceof AnalysisFragment) {
                navController.navigate(R.id.action_analysisFragment_to_AddCouponFragment, bundle);
            }
        });
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_company);
        NavDestination currentDestination = navController.getCurrentDestination();

        if (currentDestination != null) {
            int id = currentDestination.getId();
            if (id == R.id.HomeCompanyFragment || id == R.id.analysisFragment) {
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Perform logout action
                            navigateUpTo(new Intent(this, LoginActivity.class));
                        })
                        .setNegativeButton("No", null)
                        .show();
                return;
            } else if (id == R.id.AddCouponFragment) {
                navController.navigateUp();
                navController.navigate(R.id.HomeCompanyFragment);
                return;
            }
        }
        super.onBackPressed();
    }

}