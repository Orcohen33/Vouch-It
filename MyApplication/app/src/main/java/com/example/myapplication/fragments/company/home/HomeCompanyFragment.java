package com.example.myapplication.fragments.company.home;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CompanyCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentHomeCompanyBinding;
import com.example.myapplication.models.company.CompanyDetails;

import java.util.Objects;

/**
 * This code is for a fragment in an Android application that displays a list of coupons for a company.
 * The fragment first sets up a recycler view to display the coupons and creates an adapter
 * to bind the coupon data to the recycler view.
 * It then retrieves the company's ID, name, and email from the arguments passed to the fragment and uses them to initialize a HomeCompanyViewModel object.
 * When the fragment is resumed, it initializes the HomeCompanyViewModel and retrieves the company's coupons from the view model.
 * If the company has coupons, they are displayed in the recycler view, and if not, a message indicating that there are no coupons is shown to the user.
 * The fragment also has a method that allows the user to click on a coupon in the list and navigate to a new fragment to view more details about the coupon.
 */
public class HomeCompanyFragment extends Fragment implements CompanyCouponsViewAdapter.ItemClickListener {

    FragmentHomeCompanyBinding binding;

    RecyclerView recyclerView;
    CompanyCouponsViewAdapter adapter;

    HomeCompanyViewModel homeCompanyViewModel;

    CompanyDetails companyDetails;

    private Animation rotateOpen;
    private Animation rotateClose;
    private Animation fromBottom;
    private Animation toBottom;
    private boolean clicked = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rotateOpen = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(getContext(), R.anim.to_bottom_anim);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        companyDetails = new CompanyDetails(
                (sharedPreferences.getLong("id", 0)),
                (sharedPreferences.getString("fullName", "")),
                (sharedPreferences.getString("email", "")),
                (sharedPreferences.getString("token", ""))
        );

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        homeCompanyViewModel = new ViewModelProvider(this).get(HomeCompanyViewModel.class);
        binding = FragmentHomeCompanyBinding.inflate(inflater, container, false);

        // Set up the recycler view to display the coupons
        recyclerView = binding.couponCompanyList;
        adapter = new CompanyCouponsViewAdapter(
                homeCompanyViewModel.couponsTitles,
                this,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        homeCompanyViewModel.setCompanyDetails(companyDetails);     // Set the company details in the view model
        // open menu
        binding.floatingButtonCompany.setOnClickListener(v -> onAddButtonClicked());

        // Move to add/edit coupon fragment
        binding.floatingAddOrEditCouponButton.setOnClickListener(v -> {
                    if (this.getArguments() == null) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isEdit", false);
                        NavHostFragment.findNavController(HomeCompanyFragment.this)
                                .navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment, bundle);
                    } else {
                        NavHostFragment.findNavController(HomeCompanyFragment.this)
                                .navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment, this.getArguments());
                    }
                }
        );
        // Move to analysis fragment
        binding.floatingAnalysisButton.setOnClickListener(v ->
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                        .navigate(R.id.action_HomeCompanyFragment_to_analysisFragment, this.getArguments()));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (homeCompanyViewModel.couponsTitles.size() != 0) {
            homeCompanyViewModel.couponsTitles.clear();
            homeCompanyViewModel.couponsIds.clear();
        }
        homeCompanyViewModel.init();
        getCompanyCoupons();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCompanyCoupons() {
        System.out.println("getCompanyCoupons");
        if (companyDetails.getId() != null) {
            homeCompanyViewModel.getCouponResponsesLiveData().observe(getViewLifecycleOwner(), couponResponses -> {
                if (couponResponses != null && couponResponses.size() > 0) {
                    binding.noCoupons.setVisibility(View.GONE); // hide the "no coupons" text
                    for (int i = 0; i < couponResponses.size(); i++) {
                        homeCompanyViewModel.couponsTitles.add(couponResponses.get(i).getTitle());
                        homeCompanyViewModel.couponsIds.add(couponResponses.get(i).getId());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    binding.noCoupons.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onEditClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("couponTitle", homeCompanyViewModel.couponsTitles.get(position));
        bundle.putLong("couponId", homeCompanyViewModel.couponsIds.get(position));
        bundle.putBoolean("isEdit", true);
        NavHostFragment.findNavController(HomeCompanyFragment.this)
                .navigate(R.id.action_HomeCompanyFragment_to_AddCouponFragment, bundle);
    }


    @Override
    public void onDeleteClick(int position) {
        Log.d(TAG, "onDeleteClick: " + homeCompanyViewModel.couponsIds.get(position));
        homeCompanyViewModel.couponsTitles.remove(position);
        homeCompanyViewModel.deleteCouponById(homeCompanyViewModel.couponsIds.get(position));
        homeCompanyViewModel.couponsIds.remove(position);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRemoved(position);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRangeChanged(position, homeCompanyViewModel.couponsTitles.size());
    }

    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }

    private void setVisibility(boolean clicked) {
        if (!clicked) {
            binding.floatingAddOrEditCouponButton.setVisibility(View.VISIBLE);
            binding.floatingAddOrEditCouponButton.setClickable(true);
            binding.floatingAnalysisButton.setVisibility(View.VISIBLE);
            binding.floatingAnalysisButton.setClickable(true);
        } else {
            binding.floatingAddOrEditCouponButton.setVisibility(View.INVISIBLE);
            binding.floatingAddOrEditCouponButton.setClickable(false);
            binding.floatingAnalysisButton.setVisibility(View.INVISIBLE);
            binding.floatingAnalysisButton.setClickable(false);
        }
    }

    private void setAnimation(boolean clicked) {
        if (!clicked) {
            binding.floatingAddOrEditCouponButton.startAnimation(fromBottom);
            binding.floatingAnalysisButton.startAnimation(fromBottom);
            binding.floatingButtonCompany.startAnimation(rotateOpen);
        } else {
            binding.floatingAddOrEditCouponButton.startAnimation(toBottom);
            binding.floatingAnalysisButton.startAnimation(toBottom);
            binding.floatingButtonCompany.startAnimation(rotateClose);
        }
    }
}