package com.example.myapplication.models.coupon;

import com.example.myapplication.models.category.CategoryResponse;
import com.example.myapplication.models.company.CompanyResponse;
import com.google.gson.annotations.SerializedName;

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

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public CompanyResponse getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "CouponResponse{" +
                "id=" + id +
                ", title='" + title + '\n' +
                ", description='" + description + '\n' +
                ", image='" + image + '\n' +
                ", amount=" + amount + '\n' +
                ", price=" + price + '\n' +
                ", startDate='" + startDate+ '\n' +
                ", endDate='" + endDate + '\n' +
                ", category=" + category + '\n' +
                ", company=" + company + '\n' +
                '}';
    }
}
