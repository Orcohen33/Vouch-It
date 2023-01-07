package com.example.myapplication.fragments.customer.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.coupon.CouponResponse;
import com.example.myapplication.models.coupon.CouponShared;
import com.example.myapplication.repository.CategoryCouponRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    List<ItemInCategory> originalList;
    List<ItemInCategory> filteredList;

    // This is the data that we will fetch asynchronously.
    List<CouponShared> coupons;

    private CategoryCouponRepository categoryCouponRepository;

    private LiveData<List<CouponResponse>> categoryCoupons;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        originalList = new ArrayList<>();
        filteredList = new ArrayList<>();
        coupons = new ArrayList<>();
        categoryCouponRepository = new CategoryCouponRepository(application);
    }

    public void init(Long id) {
        if (categoryCoupons == null && id != null) {
            categoryCoupons = categoryCouponRepository.getCouponsByCategoryId(id);
        }
    }

    public LiveData<List<CouponResponse>> getCategoryCouponsResponseLiveData() {
        return categoryCoupons;
    }


    public List<ItemInCategory> getOriginalList() {
        return originalList;
    }

    public void setOriginalList(List<ItemInCategory> originalList) {
        this.originalList = originalList;
    }



    public static class ItemInCategory {
        private Integer image;
        private String title;
        private String price;
        private Long id;
        private String description;

        public ItemInCategory(Integer image, String title, String price, Long id, String description) {
            this.image = image;
            this.title = title;
            this.price = price;
            this.id = id;
            this.description = description;
        }

        public Integer getImage() {
            return image;
        }

        public void setImage(Integer image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
