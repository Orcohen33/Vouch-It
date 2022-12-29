package com.example.myapplication.fragments.customer.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeCustomerViewModel extends ViewModel {

    List<String> categoriesNames;
    // list of animations that receive the animation view from json files
    List<String> categoriesAnimations;

    public HomeCustomerViewModel() {
        initializeViews();

    }

    private void initializeViews() {
        categoriesNames = new ArrayList<>();
        categoriesAnimations = new ArrayList<>();

        // TODO: Change the categories and images to your own
        categoriesNames.add("אטרקציות");
        categoriesNames.add("הופעות");
        categoriesNames.add("מסעדות");
        categoriesNames.add("ספא");
        categoriesNames.add("ספורט");
        categoriesNames.add("שופינג");

        categoriesAnimations.add("restaurant");
        categoriesAnimations.add("restaurant");
        categoriesAnimations.add("restaurant");
        categoriesAnimations.add("restaurant");
        categoriesAnimations.add("restaurant");
        categoriesAnimations.add("restaurant");
    }

}