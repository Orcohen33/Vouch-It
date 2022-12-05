package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoryViewAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView categoryList;
    List<String> categories;
    List<Integer> categoriesImages;
    CategoryViewAdapter adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        categoryList = binding.categoryList;
        categories = new ArrayList<>();
        categoriesImages = new ArrayList<>();

        categories.add("Category 1");
        categories.add("Category 2");
        categories.add("Category 3");
        categories.add("Category 4");
        categories.add("Category 5");

        categoriesImages.add(R.drawable.microphone);
        categoriesImages.add(R.drawable.cart);
        categoriesImages.add(R.drawable.ic_menu_gallery);
        categoriesImages.add(R.drawable.ic_menu_slideshow);
        categoriesImages.add(R.drawable.ic_restaurant);

        adapter = new CategoryViewAdapter(categories, categoriesImages, getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        categoryList.setLayoutManager(gridLayoutManager);
        categoryList.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}