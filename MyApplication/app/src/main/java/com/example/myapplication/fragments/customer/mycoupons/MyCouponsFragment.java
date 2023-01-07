package com.example.myapplication.fragments.customer.mycoupons;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.example.myapplication.adapters.MyCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentMyCouponsBinding;

import java.util.ArrayList;
import java.util.Objects;

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

        // change the title of the action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("הקניות שלי");

        System.out.println("View created");
        mViewModel.init();
        getCompanyCoupons();

        return binding.getRoot();
    }



    @SuppressLint("NotifyDataSetChanged")
    private void getCompanyCoupons() {
        System.out.println("getCompanyCoupons");
            mViewModel.getCouponResponsesLiveData().observe(getViewLifecycleOwner(), couponResponses -> {
                if (couponResponses != null && couponResponses.size() > 0) {
                    binding.noOrdersTitleTextView.setVisibility(View.GONE); // hide the "no coupons" text
                    for (int i = 0; i < couponResponses.size(); i++) {
                        mViewModel.couponsTitles.add(couponResponses.get(i).getTitle());
                        mViewModel.couponsEndDate.add("תאריך אחרון לשימוש: "+
                                couponResponses.get(i).getEndDate());
                        mViewModel.couponsImage.add(R.drawable.no_image_icon);
                        mViewModel.couponsCode.add(""+2L) ;
                    }
                    adapter.notifyDataSetChanged();
                }
                else{
                    binding.noOrdersTitleTextView.setVisibility(View.VISIBLE);
                }
            });
    }
}