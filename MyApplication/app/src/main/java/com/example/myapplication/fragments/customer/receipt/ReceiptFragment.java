package com.example.myapplication.fragments.customer.receipt;

import static android.content.ContentValues.TAG;
import static com.example.myapplication.fragments.customer.cart.CartFragment.totalPayment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

import java.util.Objects;

public class ReceiptFragment extends Fragment {

    private FragmentReceiptBinding binding;
    private SharedViewModel model;
    private ReceiptViewModel mViewModel;
    private ReceiptViewAdapter adapter;

    RecyclerView recyclerView;

    public static ReceiptFragment newInstance() {
        return new ReceiptFragment();
    }


    @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        setHasOptionsMenu(false);

        binding = FragmentReceiptBinding.inflate(inflater, container, false);
        recyclerView = binding.receiptListItems;
        adapter = new ReceiptViewAdapter(
                mViewModel.getCouponsTitles(),
                mViewModel.getCouponsPrices(),
                mViewModel.getCouponsImages(),
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("קבלה");

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        observeCouponsData();
        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.nav_home).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void observeCouponsData() {
        model.getCoupons().observe(getViewLifecycleOwner(), couponShareds -> {
            Log.d(TAG, "onChanged: " + couponShareds.size());

            if (couponShareds.size() > 0) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        couponShareds.forEach(couponShared -> {
                        mViewModel.couponsTitles.add(couponShared.getTitle());
                        mViewModel.couponsPrices.add(couponShared.getPrice() + "");
                        mViewModel.couponsIds.add(couponShared.getId());
                        mViewModel.couponsImages.add(R.drawable.no_image_icon);
                        mViewModel.setCouponShareds(couponShareds);
                        adapter.setCouponShareds(couponShareds);
                        adapter.notifyDataSetChanged();
                        binding.totalPriceReceipt.setText(totalPayment + "");



                    });
                }
            }
        });
    }




  @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @Override
    public void onResume() {
        super.onResume();
        observeCouponsData();
    }


}