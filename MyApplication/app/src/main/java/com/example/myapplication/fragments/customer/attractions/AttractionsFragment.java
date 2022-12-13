package com.example.myapplication.fragments.customer.attractions;

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
import com.example.myapplication.databinding.FragmentSpaBinding;
import com.example.myapplication.fragments.customer.CouponsByCategoryViewModel;

public class AttractionsFragment extends Fragment {
    private final Long categoryId = 4L;
    private FragmentSpaBinding binding;
    private CouponsByCategoryViewModel attractionsViewModel;
    private CustomerCouponsViewAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        attractionsViewModel =
                new ViewModelProvider(this).get(AttractionsViewModel.class);
        binding = FragmentSpaBinding.inflate(inflater, container, false);
        recyclerView = binding.couponsCustomerList;
        adapter = new CustomerCouponsViewAdapter(
                attractionsViewModel.getCouponsImages(),
                attractionsViewModel.getCouponsTitles(),
                attractionsViewModel.getCouponsPrices(),
                getContext()
        );

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        attractionsViewModel.init(categoryId);
        getCategoryCoupons();
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategoryCoupons() {
        attractionsViewModel.getCategoryCouponsResponseLiveData().observe(getViewLifecycleOwner(), coupons -> {
            if (coupons != null && coupons.size() > 0) {
                for (int i = 0; i < coupons.size(); i++) {
                    attractionsViewModel.getCouponsImages().add(R.drawable.microphone);
                    attractionsViewModel.getCouponsTitles().add(coupons.get(i).getTitle());
                    attractionsViewModel.getCouponsPrices().add(String.valueOf(coupons.get(i).getPrice()));
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
