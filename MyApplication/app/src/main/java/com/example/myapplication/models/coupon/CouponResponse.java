package com.example.myapplication.models.coupon;

import com.example.myapplication.models.category.Category;
import com.example.myapplication.models.category.CategoryResponse;
import com.example.myapplication.models.company.CompanyResponse;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class CouponResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("amount")
    private Integer amount;
    @SerializedName("price")
    private Double price;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;
    @SerializedName("category")
    private CategoryResponse category;
    @SerializedName("company")
    private CompanyResponse company;

    public String getTitle() {
        return title;
    }
}
