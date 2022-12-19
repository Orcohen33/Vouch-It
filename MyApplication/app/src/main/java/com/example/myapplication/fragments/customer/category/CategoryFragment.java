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
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentCategoryBinding;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.models.coupon.CouponShared;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment implements CustomerCouponsViewAdapter.ItemClickListener{

    private final Long DEFAULT_CATEGORY_ID = -1L;
    private Long mCategoryId = DEFAULT_CATEGORY_ID;
    private final String DEFAULT_CATEGORY_NAME = "בית";
    private String mCategoryName = DEFAULT_CATEGORY_NAME;
    private FragmentCategoryBinding binding;
    private CategoryViewModel mViewModel;
    private SharedViewModel model;
    private CustomerCouponsViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong("categoryId", DEFAULT_CATEGORY_ID);
            mCategoryName = getArguments().getString("categoryName", DEFAULT_CATEGORY_NAME);
        }
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        recyclerView = binding.couponsCustomerList;
        adapter = new CustomerCouponsViewAdapter(
                mViewModel.getCouponsImages(),
                mViewModel.getCouponsTitles(),
                mViewModel.getCouponsPrices(),
                this,
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
                    mViewModel.getCouponsIds().add(coupons.get(i).getId());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // set the cart button to be visible
        FloatingActionButton cartButton = getActivity().findViewById(R.id.fab);
        cartButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddToCartClick(View view, int position) {
        List<CouponShared> coupons = model.getCoupons().getValue();
        if (coupons == null) {
            Toast.makeText(getContext(), "נוצר רשימת קניות חדשה", Toast.LENGTH_SHORT).show();
            coupons = new ArrayList<>();
        }
        coupons.add(new CouponShared(
                mViewModel.couponsIds.get(position),
                mViewModel.couponsTitles.get(position),
                mViewModel.couponsPrices.get(position)
        ));
        model.setCoupons(coupons);
        Toast.makeText(getContext(), "הקופון נוסף לעגלה", Toast.LENGTH_SHORT).show();
    }
}