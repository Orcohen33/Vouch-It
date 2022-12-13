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
        categoriesNames.add("ספא");
        categoriesNames.add("שופינג");
        categoriesNames.add("הופעות");
        categoriesNames.add("אטרקציות");
        categoriesNames.add("מסעדות");

        categoriesImages.add(R.drawable.microphone);
        categoriesImages.add(R.drawable.cart);
        categoriesImages.add(R.drawable.ic_menu_gallery);
        categoriesImages.add(R.drawable.ic_menu_slideshow);
        categoriesImages.add(R.drawable.ic_restaurant);

    }

}