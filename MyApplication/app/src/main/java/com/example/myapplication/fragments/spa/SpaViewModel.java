package com.example.myapplication.fragments.spa;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SpaViewModel extends ViewModel {
    List<Integer> couponsImages;
    List<String> couponsTitles;
    List<String> couponsPrices;

    public SpaViewModel() {
        initializeCoupons();
    }

    private void initializeCoupons() {
        couponsImages = new ArrayList<>();
        couponsTitles = new ArrayList<>();
        couponsPrices = new ArrayList<>();

        couponsTitles.add("Spa 1");
        couponsTitles.add("Spa 2");
        couponsTitles.add("Spa 3");
        couponsTitles.add("Spa 4");
        couponsTitles.add("Spa 5");

        couponsPrices.add("Price :10$");
        couponsPrices.add("Price :20$");
        couponsPrices.add("Price :30$");
        couponsPrices.add("Price :40$");
        couponsPrices.add("Price :50$");

        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);
        couponsImages.add(R.drawable.microphone);

    }
}