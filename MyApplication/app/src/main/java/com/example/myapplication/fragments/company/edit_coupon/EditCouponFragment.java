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
 * This code is for a fragment in an Android app that allows a company to edit a coupon.
 * The fragment contains a ViewModel object that is used to retrieve and update the coupon's information.
 * It also has input fields for the coupon's details and buttons for updating and deleting the coupon.
 * When the update button is clicked, the fragment checks that all the input fields are filled
 * out and then saves the changes to the coupon.
 * When the delete button is clicked, the fragment deletes the coupon.
 * Both buttons also navigate the user back to the previous fragment when clicked.
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
        System.out.println("get coupon details");
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
                    if(checkAllFields()) {
                        int startDateMonth = binding.editCompanyCouponStartDateInput.getMonth() + 1;
                        int endDateMonth = binding.editCompanyCouponEndDateInput.getMonth() + 1;

                        mViewModel.setArgs(
                                Objects.requireNonNull(binding.editCompanyCouponNameInput.getText()).toString(),
                                Objects.requireNonNull(binding.editCompanyCouponDescriptionInput.getText()).toString(),
                                Objects.requireNonNull(binding.editCompanyCouponPriceInput.getText()).toString(),
                                Objects.requireNonNull(binding.editCompanyCouponAmountInput.getText()).toString(),
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
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean checkAllFields() {

        boolean validFields = true;
        // check title
        if (Objects.requireNonNull(binding.editCompanyCouponNameInput.getText()).toString().isEmpty()) {
            binding.editCompanyCouponNameInput.setError("Please enter a title");
            validFields = false;
        }
        // check description
        if (Objects.requireNonNull(binding.editCompanyCouponDescriptionInput.getText()).toString().isEmpty()) {
            binding.editCompanyCouponDescriptionInput.setError("Please enter a description");
            validFields = false;
        }
        // check category
        if (binding.spinner != null && binding.spinner.getSelectedItem().toString().isEmpty()) {
        }
        // check price
        if (Objects.requireNonNull(binding.editCompanyCouponPriceInput.getText()).toString().isEmpty()) {
            binding.editCompanyCouponPriceInput.setError("Please enter a price");
            validFields = false;
        }
        // check amount
        if (Objects.requireNonNull(binding.editCompanyCouponAmountInput.getText()).toString().isEmpty()) {
            binding.editCompanyCouponAmountInput.setError("Please enter an image");
            validFields = false;
        }
        // check image
//        if (Objects.requireNonNull(binding.companyCouponImageInput.getText()).toString().isEmpty()) {
//            binding.companyCouponImageInput.setError("Please enter an image");
//        }
        // check start date
        if ((binding.editCompanyCouponStartDateInput.getYear() < 2021)) {
            binding.companyCouponStartDate.setError("Please enter a valid date");
            validFields = false;
        }
        // check end date
        if (binding.editCompanyCouponEndDateInput.getYear() < 2021 || binding.editCompanyCouponEndDateInput.getYear() < binding.editCompanyCouponStartDateInput.getYear()) {
            binding.companyCouponEndDate.setError("Please enter a valid date");
            validFields = false;
        }
        return validFields;
    }

}