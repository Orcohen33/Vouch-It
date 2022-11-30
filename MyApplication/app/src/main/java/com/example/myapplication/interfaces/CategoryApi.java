package com.example.myapplication.interfaces;

import com.example.myapplication.models.category.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {

    @GET("/api/v1/category")
    Call<List<Category>> getAllCategories();
}
