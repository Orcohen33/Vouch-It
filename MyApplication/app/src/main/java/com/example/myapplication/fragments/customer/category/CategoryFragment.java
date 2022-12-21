package com.example.myapplication.fragments.customer.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCouponsViewAdapter;
import com.example.myapplication.databinding.FragmentCategoryBinding;
import com.example.myapplication.fragments.customer.SharedViewModel;
import com.example.myapplication.models.coupon.CouponShared;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment implements CustomerCouponsViewAdapter.ItemClickListener {

    private final Long DEFAULT_CATEGORY_ID = -1L;
    private Long mCategoryId = DEFAULT_CATEGORY_ID;
    private final String DEFAULT_CATEGORY_NAME = "בית";
    private String mCategoryName = DEFAULT_CATEGORY_NAME;
    private FragmentCategoryBinding binding;
    private CategoryViewModel mViewModel;
    private SharedViewModel model;
    private CustomerCouponsViewAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryId = getArguments().getLong("categoryId", DEFAULT_CATEGORY_ID);
            mCategoryName = getArguments().getString("categoryName", DEFAULT_CATEGORY_NAME);
        }
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        recyclerView = binding.couponsCustomerList;
        adapter = new CustomerCouponsViewAdapter(
                mViewModel.originalList,
                this,
                getContext()
        );
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        // Here you choose which category to show
        mViewModel.init(mCategoryId);
        getCategoryCoupons();

        // change the title of the action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle(mCategoryName);
        // Start of SearchView
        searchView = requireActivity().findViewById(R.id.search_view);
        searchView.setVisibility(View.VISIBLE);
        getOnQueryTextListener(searchView);
        searchView.setOnCloseListener(this::onClose);
        searchView.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });
        // End of search view
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategoryCoupons() {
        mViewModel.getCategoryCouponsResponseLiveData().observe(getViewLifecycleOwner(), coupons -> {
            if (coupons != null && coupons.size() > 0) {
                for (int i = 0; i < coupons.size(); i++) {
                    mViewModel.getOriginalList().add(
                            new CategoryViewModel.ItemInCategory(
                                    R.drawable.microphone,
                                    coupons.get(i).getTitle(),
                                    coupons.get(i).getPrice().toString(),
                                    coupons.get(i).getId(),
                                    coupons.get(i).getDescription()
                            )
                    );
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // set the cart button to be visible
        FloatingActionButton cartButton = getActivity().findViewById(R.id.fab);
        cartButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddToCartClick(View view, int position) {
        List<CouponShared> coupons = model.getCoupons().getValue();
        if (coupons == null) {
            Toast.makeText(getContext(), "נוצר רשימת קניות חדשה", Toast.LENGTH_SHORT).show();
            coupons = new ArrayList<>();
        }
        coupons.add(new CouponShared(
                mViewModel.originalList.get(position).getId(),
                mViewModel.originalList.get(position).getTitle(),
                mViewModel.originalList.get(position).getPrice()
        ));
        model.setCoupons(coupons);
        Toast.makeText(getContext(), "הקופון נוסף לעגלה", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(View view, int position) {
        List<CouponShared> coupons = model.getCoupons().getValue();
        coupons.add(new CouponShared(
                mViewModel.originalList.get(position).getId(),
                mViewModel.originalList.get(position).getTitle(),
                mViewModel.originalList.get(position).getDescription()
        ));
    }

    // ----------------- Search View -----------------

    private void getOnQueryTextListener(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCoupons(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCoupons(newText);
                return true;
            }


        });
    }

    private void searchCoupons(String query) {
        mViewModel.filteredList.clear();
        for (CategoryViewModel.ItemInCategory coupon : mViewModel.originalList) {
            if (coupon.getTitle().toLowerCase().contains(query.toLowerCase())) {
                mViewModel.filteredList.add(coupon);
            }
        }
        adapter.filterList(mViewModel.filteredList);
    }

    private boolean onClose() {
        adapter.updateList(mViewModel.originalList);
        return false;
    }
    // -----------------------------------------
}