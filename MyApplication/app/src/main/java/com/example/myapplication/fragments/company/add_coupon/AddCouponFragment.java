package com.example.myapplication.fragments.company.add_coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentAddCouponBinding;
import com.example.myapplication.utils.CategoriesNames;

import java.util.Objects;

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
        companyId = this.getArguments().getLong("companyId");
        companyName = this.getArguments().getString("companyName");
        companyEmail = this.getArguments().getString("companyEmail");
        mViewModel.setCompanyName(companyName);
        mViewModel.setCompanyEmail(companyEmail);
        mViewModel.setCompanyId(companyId);

        Spinner spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, CategoriesNames.getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AddCouponSpinnerListener(mViewModel));
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.companyCouponAddButton.setOnClickListener(v -> {
            boolean validFields = checkAllFields();
            if (validFields) {
                NavHostFragment.findNavController(AddCouponFragment.this)
                        .navigateUp();


                int startDateMonth = binding.companyCouponStartDateInput.getMonth();
                int endDateMonth = binding.companyCouponEndDateInput.getMonth();
                mViewModel.setArgs(
                        Objects.requireNonNull(binding.companyCouponTitleInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString(),
                        " ",
                        (binding.companyCouponStartDateInput.getYear() + "-" +
                                (startDateMonth < 10 ? "0" + startDateMonth : startDateMonth) + "-" +
                                binding.companyCouponStartDateInput.getDayOfMonth()),
                        (binding.companyCouponEndDateInput.getYear()+"-" +
                                (endDateMonth < 10 ? "0"+endDateMonth: endDateMonth)+"-" +
                                binding.companyCouponEndDateInput.getDayOfMonth())
                        );
                mViewModel.addCoupon();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean checkAllFields() {
//        String

        boolean validFields = true;
        // check title
        if (Objects.requireNonNull(binding.companyCouponTitleInput.getText()).toString().isEmpty()) {
            binding.companyCouponTitleInput.setError("Please enter a title");
            validFields = false;
        }
        // check description
        if (Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString().isEmpty()) {
            binding.companyCouponDescriptionInput.setError("Please enter a description");
            validFields = false;
        }
        // check category
        if (binding.spinner != null && binding.spinner.getSelectedItem().toString().isEmpty()) {
        }
        // check price
        if (Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString().isEmpty()) {
            binding.companyCouponPriceInput.setError("Please enter a price");
            validFields = false;
        }
        // check amount
        if (Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString().isEmpty()) {
            binding.companyCouponAmountInput.setError("Please enter an image");
            validFields = false;
        }
        // check image
//        if (Objects.requireNonNull(binding.companyCouponImageInput.getText()).toString().isEmpty()) {
//            binding.companyCouponImageInput.setError("Please enter an image");
//        }
        // check start date
        if ((binding.companyCouponStartDateInput.getYear() < 2021)) {
            binding.companyCouponStartDate.setError("Please enter a valid date");
            validFields = false;
        }
        // check end date
        if (binding.companyCouponEndDateInput.getYear() < 2021 || binding.companyCouponEndDateInput.getYear() < binding.companyCouponStartDateInput.getYear()) {
            binding.companyCouponEndDate.setError("Please enter a valid date");
            validFields = false;
        }
        return validFields;
    }
}

