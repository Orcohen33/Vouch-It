package com.example.myapplication.fragments.company.add_or_edit_coupon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentAddOrEditCouponBinding;
import com.example.myapplication.utils.CategoriesNames;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * This class is the fragment that is shown when the user is in the add coupon page of the company.
 */
public class AddOrEditCouponFragment extends Fragment {

    private FragmentAddOrEditCouponBinding binding;
    private AddOrEditCouponViewModel mViewModel;

    private Long companyId;
    private String companyName;
    private String companyEmail;
    private boolean isEditing;
    private Long couponId = -1L;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert this.getArguments() != null;
        companyId = this.getArguments().getLong("companyId");
        companyName = this.getArguments().getString("companyName");
        companyEmail = this.getArguments().getString("companyEmail");
        isEditing = this.getArguments().getBoolean("isEdit");
        if (isEditing) {
            couponId = this.getArguments().getLong("couponId");
        }

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(isEditing? "Edit Coupon" : "Add Coupon");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddOrEditCouponViewModel.class);
        binding = FragmentAddOrEditCouponBinding.inflate(inflater, container, false);

        mViewModel.setCompanyName(companyName);
        mViewModel.setCompanyEmail(companyEmail);
        mViewModel.setCompanyId(companyId);

        Spinner spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, CategoriesNames.getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AddCouponSpinnerListener(mViewModel));

        // if the user is editing a coupon, set the fields to the coupon's values
        if (isEditing) {
            mViewModel.getCouponById(couponId);
            mViewModel.setCompanyId(companyId);
            getCouponDetails();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.companyCouponImageButtonInput.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        if (isEditing){
            binding.companyCouponAddButton.setText("Update Coupon");
        }

        binding.companyCouponAddButton.setOnClickListener(v -> {
            boolean validFields = checkAllFields();
            if (validFields) {
                NavHostFragment.findNavController(AddOrEditCouponFragment.this)
                        .navigateUp();

                int startDateMonth = binding.companyCouponStartDateInput.getMonth();
                int endDateMonth = binding.companyCouponEndDateInput.getMonth();
                mViewModel.setArgs(
                        Objects.requireNonNull(binding.companyCouponTitleInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponDescriptionInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponPriceInput.getText()).toString(),
                        Objects.requireNonNull(binding.companyCouponAmountInput.getText()).toString(),
                        (binding.companyCouponStartDateInput.getYear() + "-" +
                                (startDateMonth < 10 ? "0" + startDateMonth : startDateMonth) + "-" +
                                binding.companyCouponStartDateInput.getDayOfMonth()),
                        (binding.companyCouponEndDateInput.getYear()+"-" +
                                (endDateMonth < 10 ? "0"+endDateMonth: endDateMonth)+"-" +
                                binding.companyCouponEndDateInput.getDayOfMonth())
                        );
                if (isEditing) {
                    mViewModel.updateCoupon(couponId);
                } else {
                    mViewModel.addCoupon();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1) {
            assert data != null;
            Uri selectedImage = data.getData();
            assert selectedImage != null;
            InputStream imageStream = null;
            try {
                imageStream = requireActivity().getContentResolver().openInputStream(selectedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            assert imageStream != null;
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = imageStream.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] bytes = baos.toByteArray();
            mViewModel.setCouponImage(bytes);
        }
        System.out.println("[ON_ACTIVITY_RESULT]"+mViewModel.couponImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCouponDetails() {
        System.out.println("get coupon details");
        mViewModel.getCouponResponse().observe(getViewLifecycleOwner(), coupon -> {
            binding.companyCouponTitleInput.setText(coupon.getTitle());
            binding.companyCouponDescriptionInput.setText(coupon.getDescription());
            binding.spinner.setSelection(coupon.getCategory().getId().intValue() - 1);
            mViewModel.setCouponCategoryId(coupon.getCategory().getId());
            binding.companyCouponPriceInput.setText(String.valueOf(coupon.getPrice()));
            binding.companyCouponAmountInput.setText(String.valueOf(coupon.getAmount()));
            String[] startDate = coupon.getStartDate().split("-");
            String[] endDate = coupon.getEndDate().split("-");
            binding.companyCouponStartDateInput.updateDate(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[2]));
            binding.companyCouponEndDateInput.updateDate(Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[2]));
        });
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

