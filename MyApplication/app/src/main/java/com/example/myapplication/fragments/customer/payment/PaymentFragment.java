package com.example.myapplication.fragments.customer.payment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentPaymentBinding;

import java.util.Calendar;
import java.util.Objects;

public class PaymentFragment extends Fragment {

    private FragmentPaymentBinding binding;
    private PaymentViewModel mViewModel;


    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        Objects.requireNonNull(actionBar).setTitle("תשלום");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pay();
        binding.submitPaymentButton.setOnClickListener(v -> {
           // navigate to the next fragment
            NavController nav = Navigation.findNavController(view);
            nav.navigate(R.id.action_paymentFragment_to_receiptFragment);
        });

    }

    public void pay(){
        EditText cardNumber = binding.creditCardNumber;
        EditText cardHolderName = binding.cardHolderName;
        EditText expiryMonth = binding.expirationMonth;
        EditText expiryYear = binding.expirationYear;
        EditText cvv = binding.cvv;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        // Check validation of the fields
        EditText.OnFocusChangeListener onFocusChangeListenerCardNumber = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().length() != 16) {
                    editText.setError("Credit card number must be 16 digits");
                }
            }
        };
        cardNumber.setOnFocusChangeListener(onFocusChangeListenerCardNumber);

        EditText.OnFocusChangeListener onFocusChangeListenerCardHolderName = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                String[] name = editText.getText().toString().split(" ");
                if (editText.getText().length() < 4 || !editText.getText().toString().contains(" ") || name.length <= 1) {
                    editText.setError("- Card holder name must be at least 4 characters\n- Card holder name must be full name");
                }
            }
        };
        cardHolderName.setOnFocusChangeListener(onFocusChangeListenerCardHolderName);

        View.OnFocusChangeListener onFocusChangeListenerExpiryMonth = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().toString().isEmpty() || (editText.getText().length() != 2 ||
                        (Integer.parseInt(editText.getText().toString()) > 12 ||
                                Integer.parseInt(editText.getText().toString()) < 1))) {
                    editText.setError("- Expiry month must be 2 digits\n- Expiry month must be between 1 and 12");
                }
            }
        };
        expiryMonth.setOnFocusChangeListener(onFocusChangeListenerExpiryMonth);

        View.OnFocusChangeListener onFocusChangeListenerExpiryYear = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().toString().isEmpty() ||
                        editText.getText().length() != 4 || Integer.parseInt(editText.getText().toString()) < year) {
                    editText.setError("- Expiry year must be 4 digits\n- Expiry year must be at least 2022");
                }
            }
        };
        expiryYear.setOnFocusChangeListener(onFocusChangeListenerExpiryYear);

        View.OnFocusChangeListener onFocusChangeListenerCvv = (v, hasFocus) -> {
            if (!hasFocus) {
                EditText editText = (EditText) v;
                if (editText.getText().length() != 3) {
                    editText.setError("CVV must be 3 digits");
                }
            }
        };
        cvv.setOnFocusChangeListener(onFocusChangeListenerCvv);
    }
}