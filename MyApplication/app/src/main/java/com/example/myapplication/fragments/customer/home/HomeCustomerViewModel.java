package com.example.myapplication.fragments.customer.home;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HomeCustomerViewModel extends ViewModel {

    List<String> categoriesNames;
    List<Integer> categoriesImages;

    public HomeCustomerViewModel() {
        initializeViews();

    }

    private void initializeViews() {
        categoriesNames = new ArrayList<>();
        categoriesImages = new ArrayList<>();

        // TODO: Change the categories and images to your own
        categoriesNames.add("אטרקציות");
        categoriesNames.add("הופעות");
        categoriesNames.add("מסעדות");
        categoriesNames.add("ספא");
        categoriesNames.add("ספורט");
        categoriesNames.add("שופינג");

        categoriesImages.add(R.drawable.atractions_icon);
        categoriesImages.add(R.drawable.shows_icon);
        categoriesImages.add(R.drawable.restaurant_icon);
        categoriesImages.add(R.drawable.spa_icon);
        categoriesImages.add(R.drawable.sport_icon);
        categoriesImages.add(R.drawable.shopping_bag_icon);

    }

}