package com.example.myapplication.fragments.customer.cardpayment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

import com.example.myapplication.databinding.FragmentPaymentBinding;
import com.example.myapplication.fragments.customer.cart.CartFragment;
import com.example.myapplication.utils.paymentCard.Card;
import com.example.myapplication.utils.paymentCard.OnPayBtnClickListner;

import java.util.Date;

public class PaymentFragment extends Fragment {

    private PaymentViewModel mViewModel;
    private FragmentPaymentBinding binding;
    boolean isBackShowing = false;



    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        SearchView searchView = requireActivity().findViewById(R.id.search_view);
        searchView.setVisibility(View.GONE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        binding.btnPay.setOnClickListener(v -> {
            // navigate to the next fragment
            NavController nav = Navigation.findNavController(view);
            nav.navigate(R.id.action_paymentFragment_to_receiptFragment);
        });



    }
    public Card getCard() {
        String[] expiry = getString(binding.expiryDate).split(String.valueOf('/'));
        int month = 0, year = 0;
        if (expiry.length >= 2) {
            month = Integer.parseInt(expiry[0]);
            year = Integer.parseInt(parseDate(expiry[1]));
        }


        return new Card(getString(binding.cardNumber).replaceAll(String.valueOf(' '), "")
                , month, year, getString(binding.cvc), getString(binding.cardName), "", "", "", "", "", "", "");
    }

    private String parseDate(String str) {

        int year = Integer.parseInt(str);

        // Allow 5 years in the future for a 2 digit date
        if (year + 100 > new Date().getYear() + 5) {
            year = year + 1900;
        } else {
            year = year + 2000;
        }
        return String.valueOf(year);
    }


    private String getString(EditText ed) {
        return ed.getText().toString().trim();
    }
    @SuppressLint("SetTextI18n")
    private void init() {

        String cardNameError = "Correct Card Name is requierd";
        String cardNumberError = "Correct Card Number is requierd";
        String cvcError = "Correct  cvc is requierd";
        String expiryDateError = "Correct  expiry date is requierd";
        OnPayBtnClickListner onPayBtnClickListner = null;

        /* inflate views */

        EditText cardName = binding.cardName;
        EditText cardNumber = binding.cardNumber;
        EditText cvv = binding.cvc;
        EditText expiryDate = binding.expiryDate;

       TextView previewCardName = binding.cardPreviewName;
       TextView previewCardNumber = binding.cardPreviewNumber;
       TextView previewCvc = binding.cardPreviewCvc;
       TextView previewExpiry = binding.cardPreviewExpiry;
       TextView paymentAmount = binding.paymentAmount;
       TextView paymentAmountTextHolder = binding.paymentAmountHolder;
       TextView previewCardType = binding.cardPreviewType;

       ViewGroup cardFront = binding.cardPreviewFront;
       ViewGroup cardBack = binding.cardPreviewBack;

       Button btnPay = binding.btnPay;

        paymentAmount.setText(CartFragment.total); // take the amount from the previous fragment


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(getString(cardName))) {
                    cardName.setError(cardNameError);
                }
                if (TextUtils.isEmpty(getString(cardNumber))) {
                    cardNumber.setError(cardNumberError);
                }

                if (TextUtils.isEmpty(getString(cvv))) {
                    cvv.setError(cvcError);
                }
                if (TextUtils.isEmpty(getString(expiryDate))) {
                    expiryDate.setError(expiryDateError);

                }

                if (cardIsvalid()) {
                    onPayBtnClickListner.onClick(getCard());
                }
            }
        });


        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Remove spacing char
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    final char c = editable.charAt(editable.length() - 1);
                    if (' ' == c) {
                        editable.delete(editable.length() - 1, editable.length());
                    }
                }
                // Insert char where needed.
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    char c = editable.charAt(editable.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(editable.toString(), String.valueOf(' ')).length <= 3) {
                        editable.insert(editable.length() - 1, String.valueOf(' '));

                    }
                }

                if (editable.length() >= 16) {
                    previewCardType.setText(new Card(editable.toString(), 0, 0, "").getBrand());
                }
               //editable.charAt(0) + editable.charAt(1) + editable.charAt(2) + editable.charAt(3) + "********" + editable.charAt(12) + " " + editable.charAt(13) + " " + editable.charAt(14) + " " + editable.charAt(15);
                previewCardNumber.setText(editable.toString());
            }
        });

        expiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                switch (editable.length()) {
                    case 1:
                        if (Integer.parseInt(editable.toString()) > 1) {
                            editable.clear();
                        }
                        break;

                    case 2:
                        if (((int) editable.charAt(0)) > 0) {
                            if (((int) editable.charAt(1)) > 2) {
                                editable.delete(1, 1);
                            }
                        }
                }

                if (editable.length() > 0 && (editable.length() % 3) == 0) {
                    char c = editable.charAt(editable.length() - 1);

                    if (Character.isDigit(c)) {
                        editable.insert(editable.length() - 1, String.valueOf('/'));

                    }
                }

                previewExpiry.setText(editable.toString());
            }
        });


        cardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().trim().length() > 0) {
                    previewCardName.setText(editable.toString());
                }
            }
        });


        cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    previewCvc.setText(editable.toString());
                }
            }
        });


        cvv.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) showBack();
                    }
                });

        cardName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });

        cardNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });

        expiryDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });
    }

    private boolean cardIsvalid() {
        String cardNameError = "Correct Card Name is requierd";
        String cardNumberError = "Correct Card Number is requierd";
        String cvcError = "Correct  cvc is requierd";
        String expiryDateError = "Correct  expiry date is requierd";
        Card card = getCard();
        if (!card.validateNumber()) {
            binding.cardNumber.setError(cardNumberError);
        }
        if (!card.validateExpiryDate()) {
            binding.expiryDate.setError(expiryDateError);
        }
        if (!card.validateCVC()) {
            binding.cvc.setError(expiryDateError);
        }

        return card.validateCard();
    }

    private void showBack() {
        if (!isBackShowing) {
            @SuppressLint("ResourceType") Animator cardFlipLeftIn = AnimatorInflater.loadAnimator(getContext(), R.anim.card_flip_left_in);
            cardFlipLeftIn.setTarget(binding.cardPreviewFront);
            cardFlipLeftIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    binding.cardPreviewFront.setVisibility(GONE);
                    binding.cardPreviewBack.setVisibility(VISIBLE);
                    isBackShowing = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            cardFlipLeftIn.start();
        }
    }


    private void showFront() {
        if (isBackShowing) {
            @SuppressLint("ResourceType") Animator cardFlipRightIn = AnimatorInflater.loadAnimator(getContext(), R.anim.card_flip_right_in);
            cardFlipRightIn.setTarget(binding.cardPreviewBack);
            cardFlipRightIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    binding.cardPreviewBack.setVisibility(GONE);
                    binding.cardPreviewFront.setVisibility(VISIBLE);
                    isBackShowing = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            cardFlipRightIn.start();

        }

    }




}