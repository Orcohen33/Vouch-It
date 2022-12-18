package com.example.myapplication.fragments.customer.sport;

import android.annotation.SuppressLint;
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

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentSportBinding;
import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class SportFragment extends Fragment {
    private final Long categoryId = 6L;

    private FragmentSportBinding binding;
    private CouponsByCategoryViewModel sportViewModel;
    private CustomerCouponsViewAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sportViewModel =
                new ViewModelProvider(this).get(SportViewModel.class);
        binding = FragmentSportBinding.inflate(inflater, container, false);
        recyclerView = binding.couponsCustomerList;
        adapter = new CustomerCouponsViewAdapter(
                sportViewModel.getCouponsImages(),
                sportViewModel.getCouponsTitles(),
                sportViewModel.getCouponsPrices(),
                getContext()
        );

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

//        spaViewModel.init(categoryId);
//        getCategoryCoupons();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        sportViewModel.init(categoryId);
        getCategoryCoupons();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategoryCoupons() {
        sportViewModel.getCategoryCouponsResponseLiveData().observe(getViewLifecycleOwner(), coupons -> {
            if (coupons != null && coupons.size() > 0) {
                for (int i = 0; i < coupons.size(); i++) {
                    sportViewModel.getCouponsImages().add(R.drawable.microphone);
                    sportViewModel.getCouponsTitles().add(coupons.get(i).getTitle());
                    sportViewModel.getCouponsPrices().add(String.valueOf(coupons.get(i).getPrice()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}