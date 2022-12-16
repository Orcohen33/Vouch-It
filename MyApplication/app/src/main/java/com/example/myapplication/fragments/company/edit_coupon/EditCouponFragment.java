package com.example.myapplication.fragments.company.edit_coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentEditCouponCompanyBinding;
import com.example.myapplication.utils.CategoriesNames;

import java.util.Objects;

/**
 * This class is the fragment that is shown when the user is in the edit coupon page of the company.
 */
public class EditCouponFragment extends Fragment {

    private FragmentEditCouponCompanyBinding binding;
    private EditCouponViewModel mViewModel;
    Long couponId;
    Long companyId;
    String companyName;
    String companyEmail;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mViewModel = new ViewModelProvider(this).get(EditCouponViewModel.class);
        binding = FragmentEditCouponCompanyBinding.inflate(inflater, container, false);

        if (this.getArguments() != null) {
            couponId = this.getArguments().getLong("couponId");
            companyId = this.getArguments().getLong("companyId");
            companyName = this.getArguments().getString("companyName");
            companyEmail = this.getArguments().getString("companyEmail");
        }
        Spinner spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, CategoriesNames.getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new EditCouponSpinnerListener(mViewModel));
        mViewModel.getCouponById(couponId);
        mViewModel.setCompanyId(companyId);
        getCouponDetails();
        return binding.getRoot();

    }

    private void getCouponDetails() {
        mViewModel.getCouponResponse().observe(getViewLifecycleOwner(), coupon -> {
            binding.editCompanyCouponNameInput.setText(coupon.getTitle());
            binding.editCompanyCouponDescriptionInput.setText(coupon.getDescription());
            binding.spinner.setSelection(coupon.getCategory().getId().intValue() - 1);
            mViewModel.setCouponCategoryId(coupon.getCategory().getId());
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

                    int startDateMonth = binding.editCompanyCouponStartDateInput.getMonth() + 1;
                    int endDateMonth = binding.editCompanyCouponEndDateInput.getMonth() + 1;

                    mViewModel.setArgs(
                            Objects.requireNonNull(binding.editCompanyCouponNameInput.getText()).toString(),
                            Objects.requireNonNull(binding.editCompanyCouponDescriptionInput.getText()).toString(),
                            Objects.requireNonNull(binding.editCompanyCouponPriceInput.getText()).toString(),
                            Objects.requireNonNull(binding.editCompanyCouponAmountInput.getText()).toString(),
                            "",
                            (binding.editCompanyCouponStartDateInput.getYear() + "-" +
                                    (startDateMonth < 10 ? "0" + startDateMonth : startDateMonth) + "-" +
                                    binding.editCompanyCouponStartDateInput.getDayOfMonth()),
                            (binding.editCompanyCouponEndDateInput.getYear() + "-" +
                                    (endDateMonth < 10 ? "0" + endDateMonth : endDateMonth) + "-" +
                                    binding.editCompanyCouponEndDateInput.getDayOfMonth())
                    );
                    mViewModel.updateCoupon(couponId);
                    NavHostFragment.findNavController(EditCouponFragment.this)
                            .navigateUp();
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}