package com.example.myapplication.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomAdapter;
import com.example.myapplication.interfaces.CategoryApi;
import com.example.myapplication.models.category.Category;
import com.example.myapplication.network.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<String> categories;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.category_recycler_view);

        findViewById(R.id.menu_image_view).setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.END);
            System.out.println("Menu clicked");
        });

        categories = new ArrayList<>();
        storeCategoriesInArray();
        System.out.println("Categories: x1x1x1 " + categories);
        customAdapter = new CustomAdapter(MainActivity.this, categories);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    List<String> storeCategoriesInArray() {

        CategoryApi categoryApi = RetrofitService.getInstance().getRetrofit().create(CategoryApi.class);

        Call<List<Category>> call = categoryApi.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body();
                    assert categoryList != null;
                    for (Category category : categoryList) {
                        categories.add(category.getName());
                        System.out.println("[ON RESPONSE] Category: " + category.getName());
                    }
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

        System.out.println("Categories: x2x2x2 " + categories);
        return categories;
    }
}