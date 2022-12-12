package com.example.myapplication.fragments.company.home;

import android.annotation.SuppressLint;
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

    FragmentHomeCompanyBinding binding;

    RecyclerView recyclerView;
    CompanyCouponsViewAdapter adapter;

    HomeCompanyViewModel homeCompanyViewModel;

    Long companyId;
    String companyName;
    String companyEmail;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        homeCompanyViewModel = new ViewModelProvider(this).get(HomeCompanyViewModel.class);
        binding = FragmentHomeCompanyBinding.inflate(inflater, container, false);

        recyclerView = binding.couponCompanyList;
        adapter = new CompanyCouponsViewAdapter(
                homeCompanyViewModel.couponsTitles,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (this.getArguments() != null) {
            companyId = this.getArguments().getLong("id");
            companyName = this.getArguments().getString("name");
            companyEmail = this.getArguments().getString("email");
            homeCompanyViewModel.setId(companyId);
            homeCompanyViewModel.setName(companyName);
            homeCompanyViewModel.setEmail(companyEmail);
            homeCompanyViewModel.init();
            getCompanyCoupons();
        }


        return binding.getRoot();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCompanyCoupons() {
        if (companyId != null) {
            homeCompanyViewModel.getCouponResponsesLiveData().observe(getViewLifecycleOwner(), couponResponses -> {
                if (couponResponses != null && couponResponses.size() > 0) {
                    binding.noCoupons.setVisibility(View.GONE); // hide the "no coupons" text
                    for (int i = 0; i < couponResponses.size(); i++) {
                        homeCompanyViewModel.couponsTitles.add(couponResponses.get(i).getTitle());
                    }
                    adapter.notifyDataSetChanged();
                }
                else{
                    binding.noCoupons.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Move to the edit coupon fragment.
        binding.buttonFirst.setOnClickListener(view1 ->
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                        .navigate(R.id.action_HomeCompanyFragment_to_EditCouponFragment, this.getArguments()));

        // Move to the add coupon fragment
        binding.addNewCoupon.setOnClickListener(view1 ->
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                        .navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment, this.getArguments()));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        homeCompanyViewModel.couponsTitles.clear();
    }

}