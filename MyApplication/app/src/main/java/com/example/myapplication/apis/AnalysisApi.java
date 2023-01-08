package com.example.myapplication.apis;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AnalysisApi {

    @GET("/api/v1/analysis/{companyId}")
    Call<HashMap<String, HashMap<String, Long>>> getAnalysisData(@Path("companyId") Long companyId, @Header("Authorization") String jwtToken);

}
