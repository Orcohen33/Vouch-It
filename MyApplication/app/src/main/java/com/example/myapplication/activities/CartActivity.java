package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.databinding.ActivityCartBinding;
import com.example.myapplication.fragments.customer.cart.CartViewModel;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;

    private CartViewModel mViewModel;
    CustomerCartViewAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        recyclerView = binding.cartListItems;
        adapter = new CustomerCartViewAdapter(
                mViewModel.getCouponsTitles(),
                mViewModel.getCouponsPrices(),
                mViewModel,
                this
        );
        setContentView(R.layout.activity_cart);


    }
    public void onBackCartImageClick(View view) {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }
   //on click of the button
    public void onButtonClick(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);

    }



}
