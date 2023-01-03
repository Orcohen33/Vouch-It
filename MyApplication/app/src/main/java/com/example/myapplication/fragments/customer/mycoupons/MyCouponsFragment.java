package com.example.myapplication.fragments.customer.mycoupons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.adapters.MyCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentMyCouponsBinding;

import java.util.ArrayList;

public class MyCouponsFragment extends Fragment {

    private MyCouponsViewModel mViewModel;
    private FragmentMyCouponsBinding binding;
    public static ArrayList<String> couponsTitle = new ArrayList <>();
    MyCouponsViewAdapter adapter; // adapter for the recycler view
    RecyclerView recyclerView; // recycler view for the fragment

    public static MyCouponsFragment newInstance() {
        return new MyCouponsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MyCouponsViewModel.class);
        binding = FragmentMyCouponsBinding.inflate(inflater, container, false);
        recyclerView = binding.myCouponListItems;
        adapter = new MyCouponsViewAdapter(
                mViewModel.couponsTitles,
                mViewModel.couponsCode,
                mViewModel.couponsEndDate,
                mViewModel.couponsImage,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }

}