package com.example.myapplication.fragments.shows;

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
import com.example.myapplication.databinding.FragmentShowsBinding;

public class ShowsFragment extends Fragment {

    private FragmentShowsBinding binding;
    private ShowViewModel showViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showViewModel =
                new ViewModelProvider(this).get(ShowViewModel.class);

        binding = FragmentShowsBinding.inflate(inflater, container, false);

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
                showViewModel.couponsImages,
                showViewModel.couponsTitles,
                showViewModel.couponsPrices,
                getContext()
        );

        // Here you can choose the span count for the grid layout
        couponsList.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        couponsList.setHasFixedSize(true);
        couponsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}