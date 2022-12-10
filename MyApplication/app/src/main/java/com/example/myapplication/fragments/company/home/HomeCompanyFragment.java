package com.example.myapplication.fragments.company.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CompanyCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentHomeCompanyBinding;

/**
 * This class is the fragment that is shown when the user is in the home page of the company.
 */
public class HomeCompanyFragment extends Fragment {

    private FragmentHomeCompanyBinding binding;
    private HomeCompanyViewModel homeCompanyViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        homeCompanyViewModel = new ViewModelProvider(this).get(HomeCompanyViewModel.class);

        binding = FragmentHomeCompanyBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Move to the edit coupon fragment.
        binding.buttonFirst.setOnClickListener(view1 ->
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                .navigate(R.id.action_HomeCompanyFragment_to_EditCouponFragment));

        // Move to the add coupon fragment
        binding.addNewCoupon.setOnClickListener(view1 ->
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                .navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment));

        // Recycler view for the coupons.
        RecyclerView couponsList = binding.couponCompanyList;
        CompanyCouponsViewAdapter adapter = new CompanyCouponsViewAdapter(
                homeCompanyViewModel.couponsTitles,
                getContext()
        );
        couponsList.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        couponsList.setHasFixedSize(true);
        couponsList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}