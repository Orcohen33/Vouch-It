package com.example.myapplication.fragments.company.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CompanyCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentHomeCompanyBinding;

public class HomeCompanyFragment extends Fragment {

    private FragmentHomeCompanyBinding binding;
    private HomeCompanyViewModel homeCompanyViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        homeCompanyViewModel = new ViewModelProvider(this).get(HomeCompanyViewModel.class);

        binding = FragmentHomeCompanyBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeCompanyFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        RecyclerView couponsList = binding.couponCompanyList;
        CompanyCouponsViewAdapter adapter = new CompanyCouponsViewAdapter(
                homeCompanyViewModel.couponsTitles,
                getContext()
        );
        couponsList.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        couponsList.setHasFixedSize(true);
        couponsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}