package com.example.myapplication.fragments.restaurants;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsViewModel extends ViewModel {
  List<Integer> couponsImages;
  List<String> couponsTitles;

  List<String> couponsPrices;

    public RestaurantsViewModel() {
        initializeCoupons();
    }

    private void initializeCoupons() {
        couponsImages = new ArrayList<>();
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();

        couponsTitles.add("Restaurant 1");
        couponsTitles.add("Restaurant 2");
        couponsTitles.add("Restaurant 3");
        couponsTitles.add("Restaurant 4");
        couponsTitles.add("Restaurant 5");

        couponsPrices.add("Price :100$");
        couponsPrices.add("Price :200$");
        couponsPrices.add("Price :300$");
        couponsPrices.add("Price :400$");
        couponsPrices.add("Price :500$");

        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);

    }
}