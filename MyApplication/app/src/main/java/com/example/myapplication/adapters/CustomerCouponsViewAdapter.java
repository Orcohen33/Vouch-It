package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.text.style.TextAppearanceSpan;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.coupon.CouponShared;
import com.google.mlkit.nl.smartreply.TextMessage;

import java.util.List;


/**
 * A custom adapter to use with the RecyclerView widget.
 * This adapter is used to display a simple list of text
 * with a title and a subtitle and image.
 */
public class CustomerCouponsViewAdapter extends RecyclerView.Adapter<CustomerCouponsViewAdapter.ViewHolder> {
    List<Integer> couponsImages;
    List<String> couponsTitles;
    List<String> couponsPrices;
    LayoutInflater inflater;
    List<String> couponsDescriptions;

    List<CouponShared> coupons;

    static ItemClickListener itemClickListener;

    //couponsDescriptions = new ArrayList<>();
    public CustomerCouponsViewAdapter(List<Integer> couponsImages, List<String> couponsTitles, List<String> couponsPrices, List<String> couponsDescriptions,ItemClickListener itemClickListener, Context ctx) {
        this.couponsImages = couponsImages;
        this.couponsTitles = couponsTitles;
        this.couponsPrices = couponsPrices;
        this.itemClickListener = itemClickListener;
        this.couponsDescriptions = couponsDescriptions;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_coupon_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer image = couponsImages.get(position);
        String title = couponsTitles.get(position);
        String price = couponsPrices.get(position);
        String description = couponsDescriptions.get(position);
        holder.image.setImageResource(image);
        holder.title.setText(title);
        holder.price.setText(price + "₪");

        // When the image button is clicked, show the description of the coupon.
        holder.image.setOnClickListener(v -> dialogForImageAction(position));
    }

    private void dialogForImageAction(int position) {
       // AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
        //builder.setTextAppearance(this, R.style.TextAppearance_Large_Bold);
      //  resolveDialogTheme ;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(inflater.getContext(), android.R.style.Theme_Light_Panel));
        builder.setTitle(couponsTitles.get(position));
        builder.setMessage(couponsDescriptions.get(position));
        builder.setIcon(couponsImages.get(position));


        builder.show();
    }

    @Override
    public int getItemCount() {
        return couponsImages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView price;
        ImageButton addToCart;
        String description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coupon_image_view);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            price = itemView.findViewById(R.id.coupon_price_text_view);
            addToCart = itemView.findViewById(R.id.add_to_cart_coupon_grid_layout);
            addToCart.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onAddToCartClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void onAddToCartClick(View view, int position);
        void onImageClick(View view, int position);
    }
}


