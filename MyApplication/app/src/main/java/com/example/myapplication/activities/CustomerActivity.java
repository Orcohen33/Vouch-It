package com.example.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCustomerBinding;
import com.example.myapplication.models.coupon.CouponShared;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class CustomerActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityCustomerBinding binding;

    private List<CouponShared> coupons;

    public boolean showSearchView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Set a Toolbar to replace the ActionBar.
        setSupportActionBar(binding.appBarMain.toolbar);
        // Set the search view be GONE by default
        binding.appBarMain.searchView.setVisibility(View.GONE);

        Objects.requireNonNull(getSupportActionBar()).setTitle("בית");
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(binding.appBarMain.toolbar, navController, new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawer).build());

//         Set up a listener for the navigation view's menu items
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            boolean isInNavHome = Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.nav_home;
            long categoryId = -1L;
            String categoryName = "";
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    if (isInNavHome) {
                        navController.navigate(R.id.action_nav_home_self);
                    } else {
                        navController.navigate(R.id.action_nav_category_to_nav_home2);
                    }
                    drawer.close();
                    return true;
                case R.id.nav_attractions:
                    categoryId = 4L;
                    categoryName = "אטרקציות";
                    break;
                case R.id.nav_shows:
                    categoryId = 3L;
                    categoryName = "הופעות";
                    break;
                case R.id.nav_restaurants:
                    categoryId = 5L;
                    categoryName = "מסעדות";
                    break;
                case R.id.nav_spa:
                    categoryId = 1L;
                    categoryName = "ספא";
                    break;
                case R.id.nav_sport:
                    categoryId = 6L;
                    categoryName = "ספורט";
                    break;
                case R.id.nav_shopping:
                    categoryId = 2L;
                    categoryName = "שופינג";
                    break;
                case R.id.nav_my_coupons:
                    navController.navigate(R.id.nav_my_coupons);
                    drawer.closeDrawers();
                    return true;
            }
            Bundle bundle = new Bundle();
            bundle.putLong("categoryId", categoryId);
            bundle.putString("categoryName", categoryName);
            if (isInNavHome) {
                navController.navigate(R.id.action_nav_home_to_nav_category, bundle);
            } else {
                navController.navigate(R.id.action_nav_category_self);
            }
//            Close the drawer
            drawer.closeDrawers();
            return true;
        });
        binding.appBarMain.fab.setOnClickListener(view -> {
            boolean isInNavHome = Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.nav_home;
            Bundle bundle = getIntent().getExtras();
            if (isInNavHome)
                navController.navigate(R.id.action_nav_home_to_cartFragment2, bundle);
            else
                navController.navigate(R.id.action_nav_category_to_cartFragment, bundle);
            binding.appBarMain.fab.setVisibility(View.GONE);
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.appBarMain.fab.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sharedPreferences.getString("fullName", "");
        String email = sharedPreferences.getString("email", "");
        if (name != null && email != null) {
            ((TextView) binding.navView.getHeaderView(0).findViewById(R.id.nav_header_name)).setText(name);
            ((TextView) binding.navView.getHeaderView(0).findViewById(R.id.nav_header_email)).setText(email);

        }
    }

}