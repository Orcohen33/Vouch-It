package com.example.myapplication.fragments.customer.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.CategoryViewAdapter;
import com.example.myapplication.databinding.FragmentHomeCustomerBinding;

public class HomeCustomerFragment extends Fragment {

    private FragmentHomeCustomerBinding binding;
    private HomeCustomerViewModel homeCustomerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeCustomerViewModel =
                new ViewModelProvider(this).get(HomeCustomerViewModel.class);
        binding = FragmentHomeCustomerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView categoryList = binding.categoryList;
        CategoryViewAdapter adapter = new CategoryViewAdapter(homeCustomerViewModel.categoriesNames,
                homeCustomerViewModel.categoriesImages,
                getContext()
        );
        // Here you can choose the span count for the grid layout
        categoryList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        categoryList.setHasFixedSize(true);
        categoryList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}