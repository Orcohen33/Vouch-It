package com.example.myapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.interfaces.AnalysisApi;
import com.example.myapplication.network.RetrofitService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisRepository {
    AnalysisApi analysisApi;

    public AnalysisRepository() {
        analysisApi = RetrofitService.getInstance().getRetrofit().create(AnalysisApi.class);
    }

    public LiveData<HashMap<String, HashMap<String, Long>>> getAnalysisData(Long companyId) {
        final MutableLiveData<HashMap<String, HashMap<String, Long>>> data = new MutableLiveData<>();
        analysisApi.getAnalysisData(companyId).enqueue(new Callback<HashMap<String, HashMap<String, Long>>>() {
            @Override
            public void onResponse(Call<HashMap<String, HashMap<String, Long>>> call, Response<HashMap<String, HashMap<String, Long>>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, HashMap<String, Long>>> call, Throwable t) {
                System.out.println("AnalysisRepository.onFailure");
            }
        });
        return data;
    }

}
