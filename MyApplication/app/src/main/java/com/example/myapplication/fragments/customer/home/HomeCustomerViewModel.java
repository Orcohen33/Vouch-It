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
        categoriesNames.add("Category 1");
        categoriesNames.add("Category 2");
        categoriesNames.add("Category 3");
        categoriesNames.add("Category 4");
        categoriesNames.add("Category 5");

        categoriesImages.add(R.drawable.microphone);
        categoriesImages.add(R.drawable.cart);
        categoriesImages.add(R.drawable.ic_menu_gallery);
        categoriesImages.add(R.drawable.ic_menu_slideshow);
        categoriesImages.add(R.drawable.ic_restaurant);

    }

}