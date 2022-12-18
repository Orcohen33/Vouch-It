package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CustomerCartViewAdapter extends RecyclerView.Adapter<CustomerCartViewAdapter.ViewHolder> {

    List<String> couponsTitles;
    List<String> couponsPrices;
    ItemClickListener itemClickListener;
    LayoutInflater inflater;

    public CustomerCartViewAdapter(List<String> couponsTitles, List<String> couponsPrices, ItemClickListener itemClickListener, Context ctx) {
        this.couponsTitles = couponsTitles;
        this.couponsPrices = couponsPrices;
        this.itemClickListener = itemClickListener;
        this.inflater = LayoutInflater.from(ctx);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_coupon_cart_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = couponsTitles.get(position);
        String price = couponsPrices.get(position);

        holder.title.setText(title);
        holder.price.setText(price);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            price = itemView.findViewById(R.id.coupon_price_text_view);
            delete = itemView.findViewById(R.id.remove_from_cart_coupon_grid_layout);
        }
    }

    public interface ItemClickListener {
        void onDeleteClick(View view, int position);
        void onAddToCartClick(View view, int position);
    }
}
