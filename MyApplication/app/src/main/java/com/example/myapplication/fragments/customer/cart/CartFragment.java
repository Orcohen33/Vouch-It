package com.example.myapplication.fragments.customer.cart;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CustomerCartViewAdapter;
import com.example.myapplication.databinding.FragmentCartBinding;
import com.example.myapplication.fragments.customer.SharedViewModel;

import java.util.Objects;

public class CartFragment extends Fragment implements CustomerCartViewAdapter.ItemClickListener {
    public static String totalCart;
    public static String totalPayment;
    private FragmentCartBinding binding; // binding for the fragment
    private CartViewModel mViewModel; // view model for the fragment
    private SharedViewModel model; // shared view model for the fragment
    CustomerCartViewAdapter adapter; // adapter for the recycler view
    RecyclerView recyclerView; // recycler view for the fragment
    Long customerId; // customer id


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = FragmentCartBinding.inflate(inflater, container, false);
        recyclerView = binding.cartListItems;
        adapter = new CustomerCartViewAdapter(mViewModel.getCouponsTitles(), mViewModel.getCouponsPrices(), this, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        // change the title of the action bar
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("עגלת קניות");

        // this is the shared view model
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            customerId = getArguments().getLong("customerId");
        }
        // when the user clicks on the pay button, the user is redirected to the payment page
        binding.paymentButton.setOnClickListener(v -> {
            if (mViewModel.couponsIds.size() > 0) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_cartFragment_to_paymentTestFragment);
            } else {
                // Create an alert dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // Set the message to display
                builder.setMessage("עגלת קניות ריקה");

                // Set the title for the dialog
                builder.setTitle("שגיאה");

                // Set the negative button to dismiss the dialog
                builder.setNegativeButton("סגור", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                });

                // Create the dialog
                AlertDialog dialog = builder.create();

                // Show the dialog
                dialog.show();

            }
        });
        SearchView searchView = requireActivity().findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);
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
                binding.price.setText(mViewModel.getTotalPriceFormat());
                totalCart = mViewModel.getTotalPriceFormat();
                totalPayment = mViewModel.getTotalPriceFormatAfterPayment();
            } else {
                binding.noCartCoupons.setVisibility(View.VISIBLE);
                binding.price.setText("סה\"כ: 0₪");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        observeCouponsData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        binding = null;
    }

    @Override
    public void onDeleteClick(int position) {
        // Create an alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        // Set the message to display
        builder.setMessage("אתה בטוח במחיקה?");
        // Set the title for the dialog
        builder.setTitle("אזהרה");
        // Set the warning icon
        builder.setIcon(android.R.drawable.ic_delete);
        // Set the positive button to confirm the action
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform the delete action
                mViewModel.couponsTitles.remove(position);
                mViewModel.couponsPrices.remove(position);
                mViewModel.couponsIds.remove(position);
                mViewModel.mDetails.remove(position);
                mViewModel.updateTotalPrice();
                binding.price.setText(mViewModel.getTotalPriceFormat());
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRemoved(position);
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemRangeChanged(position, mViewModel.couponsTitles.size());
                totalCart = mViewModel.getTotalPriceFormat();
                totalPayment = mViewModel.getTotalPriceFormatAfterPayment();
            }
        });
        // Set the negative button to cancel the action
        builder.setNegativeButton("לא", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });
        // Create the dialog
        AlertDialog dialog = builder.create();
        // Show the dialog
        dialog.show();
    }
}
