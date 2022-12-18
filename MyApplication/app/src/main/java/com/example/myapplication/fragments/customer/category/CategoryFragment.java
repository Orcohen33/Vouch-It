package com.example.myapplication.fragments.customer.category;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentCategoryBinding;

import java.util.Objects;

public class CategoryFragment extends Fragment {

    private final Long DEFAULT_CATEGORY_ID = -1L;
    private Long mCategoryId = DEFAULT_CATEGORY_ID;
    private final String DEFAULT_CATEGORY_NAME = "בית";
    private String mCategoryName = DEFAULT_CATEGORY_NAME;
    private FragmentCategoryBinding binding;
    private CouponsByCategoryViewModel mViewModel;
    private CustomerCouponsViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong("categoryId", DEFAULT_CATEGORY_ID);
            mCategoryName = getArguments().getString("categoryName", DEFAULT_CATEGORY_NAME);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CouponsByCategoryViewModel.class);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        recyclerView = binding.couponsCustomerList;
        adapter = new CustomerCouponsViewAdapter(
                mViewModel.getCouponsImages(),
                mViewModel.getCouponsTitles(),
                mViewModel.getCouponsPrices(),
                mViewModel,
                getContext()
        );

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        mViewModel.init(mCategoryId);
        getCategoryCoupons();
        // change the title of the action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(mCategoryName);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategoryCoupons() {
        mViewModel.getCategoryCouponsResponseLiveData().observe(getViewLifecycleOwner(), coupons -> {
            if (coupons != null && coupons.size() > 0) {
                for (int i = 0; i < coupons.size(); i++) {
                    mViewModel.getCouponsImages().add(R.drawable.microphone);
                    mViewModel.getCouponsTitles().add(coupons.get(i).getTitle());
                    mViewModel.getCouponsPrices().add(String.valueOf(coupons.get(i).getPrice()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}