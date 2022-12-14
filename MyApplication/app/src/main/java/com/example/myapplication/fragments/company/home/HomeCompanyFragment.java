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

import java.util.Objects;

/**
 * This class is the fragment that is shown when the user is in the home page of the company.
 */
public class HomeCompanyFragment extends Fragment implements CompanyCouponsViewAdapter.ItemClickListener {

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
                this,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (this.getArguments() != null) {
            companyId = this.getArguments().getLong("companyId");
            companyName = this.getArguments().getString("companyName");
            companyEmail = this.getArguments().getString("companyEmail");
            homeCompanyViewModel.setId(companyId);
            homeCompanyViewModel.setName(companyName);
            homeCompanyViewModel.setEmail(companyEmail);
            homeCompanyViewModel.init();
            getCompanyCoupons();
        }

        // sort the coupons by name and then show it to user

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
                        homeCompanyViewModel.couponsIds.add(couponResponses.get(i).getId());
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

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", companyId);
        bundle.putString("name", companyName);
        bundle.putString("email", companyEmail);
        bundle.putString("couponTitle", homeCompanyViewModel.couponsTitles.get(position));
        bundle.putLong("couponId",homeCompanyViewModel.couponsIds.get(position));
        NavHostFragment.findNavController(HomeCompanyFragment.this)
                .navigate(R.id.action_HomeCompanyFragment_to_EditCouponFragment, bundle);
    }
}