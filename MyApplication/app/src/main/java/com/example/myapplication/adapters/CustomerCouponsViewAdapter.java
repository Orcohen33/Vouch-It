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

    public CustomerCouponsViewAdapter(List<Integer> couponsImages, List<String> couponsTitles, List<String> couponsPrices, Context ctx) {
        this.couponsImages = couponsImages;
        this.couponsTitles = couponsTitles;
        this.couponsPrices = couponsPrices;
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
        holder.image.setImageResource(image);
        holder.title.setText(title);
        holder.price.setText(price);

    }

    @Override
    public int getItemCount() {
        return couponsImages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coupon_image_view);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            price = itemView.findViewById(R.id.coupon_price_text_view);
        }
    }
}


