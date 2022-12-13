package com.example.myapplication.models.coupon;

import androidx.annotation.Size;

import com.example.myapplication.models.category.CategoryRequest;
import com.example.myapplication.models.company.CompanyRequest;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class CouponRequest {


    @Size(min = 3, max = 50)
    private String title;
    @Size(min = 3, max = 255)
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer amount;
    private Double price;
    private String image;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private Long companyId;
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CouponRequest{" +
                "title='" + title + '\n' +
                ", description='" + description + '\n' +
                ", startDate=" + startDate + '\n' +
                ", endDate=" + endDate + '\n' +
                ", amount=" + amount + '\n' +
                ", price=" + price + '\n' +
                ", image='" + image + '\n' +
                ", companyId=" + companyId + '\n' +
                ", categoryId=" + categoryId + '\n' +
                '}';
    }
}
