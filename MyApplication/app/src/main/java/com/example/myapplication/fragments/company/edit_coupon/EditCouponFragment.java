package com.example.myapplication.fragments.company.edit_coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentEditCouponCompanyBinding;

/**
 * This class is the fragment that is shown when the user is in the edit coupon page of the company.
 */
public class EditCouponFragment extends Fragment {

    private FragmentEditCouponCompanyBinding binding;
    private EditCouponViewModel editCouponViewModel;
    Long couponId;
    Long companyId;
    String companyName;
    String companyEmail;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        editCouponViewModel = new ViewModelProvider(this).get(EditCouponViewModel.class);
        binding = FragmentEditCouponCompanyBinding.inflate(inflater, container, false);

        if (this.getArguments() != null) {
            couponId = this.getArguments().getLong("couponId");
            companyId = this.getArguments().getLong("companyId");
            companyName = this.getArguments().getString("companyName");
            companyEmail = this.getArguments().getString("companyEmail");
        }
        editCouponViewModel.init(couponId);
        getCouponDetails();
        return binding.getRoot();

    }

    private void getCouponDetails() {
        editCouponViewModel.getCouponResponse().observe(getViewLifecycleOwner(), coupon -> {
            binding.editCompanyCouponNameInput.setText(coupon.getTitle());
            binding.editCompanyCouponDescriptionInput.setText(coupon.getDescription());
            binding.editCompanyCouponPriceInput.setText(String.valueOf(coupon.getPrice()));
            binding.editCompanyCouponAmountInput.setText(String.valueOf(coupon.getAmount()));
            String[] startDate = coupon.getStartDate().split("-");
            String[] endDate = coupon.getEndDate().split("-");
            binding.editCompanyCouponStartDateInput.updateDate(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[2]));
            binding.editCompanyCouponEndDateInput.updateDate(Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[2]));
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Update coupon button
        binding.companyCouponUpdateButton.setOnClickListener(view1 ->
            {
                // TODO: ADD UPDATE COUPON FUNCTIONALITY
            NavHostFragment.findNavController(EditCouponFragment.this)
                .navigate(R.id.action_EditCouponFragment_to_HomeCompanyFragment);
            }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}