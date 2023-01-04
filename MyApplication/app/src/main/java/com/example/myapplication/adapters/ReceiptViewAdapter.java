package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;

/*
    * Adapter for the recycler view in the receipt fragment
    * It is used to display the coupons that the customer bought
 */
public class ReceiptViewAdapter extends RecyclerView.Adapter<ReceiptViewAdapter.ViewHolder>{

    List<String> couponsTitles;
    List<String> couponsPrices;
    List<Integer> couponsImages;
    List<CouponShared> coupons;
    LayoutInflater inflater;

    public ReceiptViewAdapter(List<String> couponsTitles, List<String> couponsPrices,
                              List<Integer> couponsImage, Context ctx) {
        this.couponsTitles = couponsTitles;
        this.couponsPrices = couponsPrices;
        this.couponsImages = couponsImage;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_coupon_receipt_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = couponsTitles.get(position);
        Integer image = couponsImages.get(position);
        String price = couponsPrices.get(position);

        holder.title.setText(title);
        holder.price.setText(price);
        holder.image.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public void setCouponShareds(List<CouponShared> couponShareds) {
        this.coupons = couponShareds;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.coupon_receipt_title_text_view);
            price = itemView.findViewById(R.id.coupon_receipt_price_text_view);
            image = itemView.findViewById(R.id.coupon_image_view);
        }
    }
}
