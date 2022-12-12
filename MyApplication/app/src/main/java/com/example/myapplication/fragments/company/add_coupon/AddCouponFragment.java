package com.example.myapplication.fragments.company.add_coupon;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentAddCouponBinding;
import com.example.myapplication.models.coupon.Coupon;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class is the fragment that is shown when the user is in the add coupon page of the company.
 */
public class AddCouponFragment extends Fragment {

    private FragmentAddCouponBinding binding;
    private AddCouponViewModel mViewModel;

    private String companyName;
    private String companyEmail;

    public static AddCouponFragment newInstance() {
        return new AddCouponFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddCouponViewModel.class);
        binding = FragmentAddCouponBinding.inflate(inflater, container, false);
        assert this.getArguments() != null;
        companyName = this.getArguments().getString("name");
        companyEmail = this.getArguments().getString("email");
        System.out.println("[AddCouponFragment] Company name: " + companyName);
        System.out.println("[AddCouponFragment] Company email: " + companyEmail);
        addCouponButtonListener();
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_add_coupon, container, false);
    }

    private void addCouponButtonListener() {
//        Coupon coupon = new Coupon();
        MaterialButton addCouponButton = binding.companyCouponAddButton;
        addCouponButton.setOnClickListener(v -> {
            mViewModel.couponTitle = Objects.requireNonNull(binding.companyCouponNameInput.getText()).toString();
            mViewModel.couponDescription = Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString();
            mViewModel.couponCategory = "funny";
            mViewModel.couponPrice = Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString();
            mViewModel.couponAmount = Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString();
            mViewModel.couponImage = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mViewModel.couponStartDate = LocalDate.of(
                        Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getYear())),
                        Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getMonth() + 1)),
                        Integer.parseInt(String.valueOf(binding.companyCouponStartDateInput.getDayOfMonth()))
                );
                mViewModel.couponEndDate = LocalDate.of(
                        Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getYear())),
                        Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getMonth() + 1)),
                        Integer.parseInt(String.valueOf(binding.companyCouponEndDateInput.getDayOfMonth()))
                );
            }
            System.out.println(mViewModel);
            mViewModel.addCoupon();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}



//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddCouponViewModel.class);
//    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddCouponViewModel.class);
//
//
//        // TODO: Use the ViewModel

//    }
