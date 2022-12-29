package com.example.myapplication.fragments.company.analysis;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.repository.AnalysisRepository;

import java.util.HashMap;

public class AnalysisViewModel extends ViewModel {

    LiveData<HashMap<String, HashMap<String, Long>>> analysisData;
    HashMap<String, Long> salesPerMonth;
    HashMap<String, Long> salesPerCategoryData;
    HashMap<String, Long> totalCouponsSold;

    private AnalysisRepository analysisRepository;
    boolean isDataLoaded = false;

    public AnalysisViewModel() {
        salesPerMonth = new HashMap<>();
        salesPerCategoryData = new HashMap<>();
        totalCouponsSold = new HashMap<>();
    }

    public void init(Long companyId) {
        analysisRepository = new AnalysisRepository();
        analysisData = analysisRepository.getAnalysisData(companyId);
    }

    public LiveData<HashMap<String, HashMap<String, Long>>> getAnalysisData() {

        analysisData = Transformations.map(analysisData, input -> {
            if (input != null) {
                salesPerMonth = input.get("salesPerMonth");
                totalCouponsSold = input.get("totalCouponsSold");
                isDataLoaded = true;
            }
            return input;
        });

        return analysisData;
    }


    public HashMap<String, Long> getSalesPerMonth() {
        return salesPerMonth;
    }

    public HashMap<String, Long> getTotalCouponsSold() {
        return totalCouponsSold;
    }
}