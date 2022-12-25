package com.example.myapplication.fragments.customer.receipt;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ReceiptViewAdapter;
import com.example.myapplication.databinding.FragmentReceiptBinding;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.fragments.customer.cart.CartFragment;

import java.util.Objects;

public class ReceiptFragment extends Fragment {

    private FragmentReceiptBinding binding;
    private SharedViewModel model;
    private ReceiptViewModel mViewModel;
    ReceiptViewAdapter adapter;
    RecyclerView recyclerView;

    public static ReceiptFragment newInstance() {
        return new ReceiptFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        binding = FragmentReceiptBinding.inflate(inflater, container, false);
        recyclerView = binding.cartListItems;
        adapter = new ReceiptViewAdapter(
                CartFragment.couponsTitle,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("קבלה");

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        observeCouponsData();

        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }

    private void observeCouponsData() {
        model.getCoupons().observe(getViewLifecycleOwner(), couponShareds -> {
            Log.d(TAG, "onChanged: " + couponShareds.size());

            if (couponShareds.size() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    couponShareds.forEach(couponShared -> {
                        mViewModel.couponsTitles.add(couponShared.getTitle());

                    });
                }
            }
        });
    }


}