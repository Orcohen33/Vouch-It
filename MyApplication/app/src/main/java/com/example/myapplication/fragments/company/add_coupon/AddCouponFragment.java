package com.example.myapplication.fragments.company.add_coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentAddCouponBinding;

/**
 * This class is the fragment that is shown when the user is in the add coupon page of the company.
 */
public class AddCouponFragment extends Fragment {

    private FragmentAddCouponBinding binding;
    private AddCouponViewModel mViewModel;

    private Long companyId;
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
        companyId = this.getArguments().getLong("id");
        companyName = this.getArguments().getString("name");
        companyEmail = this.getArguments().getString("email");
        mViewModel.setCompanyName(companyName);
        mViewModel.setCompanyEmail(companyEmail);
        mViewModel.setCompanyId(companyId);
        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.companyCouponAddButton.setOnClickListener(v -> {
            mViewModel.initializeAllFields(binding);
            mViewModel.addCoupon();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

