package com.example.myapplication.fragments.spa;

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

import com.example.myapplication.adapters.CouponsViewAdapter;
import com.example.myapplication.databinding.FragmentSpaBinding;

public class SpaFragment extends Fragment {

    private FragmentSpaBinding binding;
    private SpaViewModel spaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        spaViewModel =
                new ViewModelProvider(this).get(SpaViewModel.class);

        binding = FragmentSpaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView couponsList = binding.couponsList;
        CouponsViewAdapter adapter = new CouponsViewAdapter(
                spaViewModel.couponsImages,
                spaViewModel.couponsTitles,
                spaViewModel.couponsPrices,
                getContext()
        );

        couponsList.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        couponsList.setHasFixedSize(true);
        couponsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}