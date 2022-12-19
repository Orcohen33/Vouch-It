package com.example.myapplication.fragments.customer.cart;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.fragments.customer.cart.CartViewModel;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;
import java.util.Objects;

public class CartFragment extends Fragment implements CustomerCartViewAdapter.ItemClickListener {

    private FragmentCartBinding binding; // binding for the fragment
    private CartViewModel mViewModel; // view model for the fragment
    private SharedViewModel model; // shared view model for the fragment
    CustomerCartViewAdapter adapter; // adapter for the recycler view
    RecyclerView recyclerView; // recycler view for the fragment

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = FragmentCartBinding.inflate(inflater, container, false);
        recyclerView = binding.cartListItems;
        adapter = new CustomerCartViewAdapter(
                mViewModel.getCouponsTitles(),
                mViewModel.getCouponsPrices(),
                this,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        // change the title of the action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("עגלת קניות");

        // this is the shared view model
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        observeCouponsData();

        // when the user clicks on the pay button, the user is redirected to the payment page
        binding.paymentButton.setOnClickListener(v -> {
//            navigateToPaymentFragment();
             NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_cartFragment_to_paymentFragment);
        });
        return binding.getRoot();
    }

    private void observeCouponsData() {
        model.getCoupons().observe(getViewLifecycleOwner(), couponShareds -> {
            Log.d(TAG, "onChanged: " + couponShareds.size());
            if (couponShareds.size() > 0) {
                binding.noCartCoupons.setVisibility(View.GONE);
                adapter.setCouponShareds(couponShareds);
                mViewModel.setCouponShareds(couponShareds);
                adapter.notifyDataSetChanged();
                binding.price.setText(mViewModel.getTotalPrice());
            }
            else{
                binding.noCartCoupons.setVisibility(View.VISIBLE);
                binding.price.setText("סה\"כ: 0₪");
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        binding = null;
    }

    @Override
    public void onDeleteClick(int position) {
        mViewModel.couponsTitles.remove(position);
        mViewModel.couponsPrices.remove(position);
        mViewModel.couponsIds.remove(position);
        mViewModel.mDetails.remove(position);
        mViewModel.updateTotalPrice();
        binding.price.setText(mViewModel.getTotalPrice());
        Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRemoved(position);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRangeChanged(position, mViewModel.couponsTitles.size());
    }
}
