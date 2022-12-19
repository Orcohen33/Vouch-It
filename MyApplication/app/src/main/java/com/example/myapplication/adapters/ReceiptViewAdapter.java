package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;

public class ReceiptViewAdapter extends RecyclerView.Adapter<ReceiptViewAdapter.ViewHolder>{
    List<String> couponsTitles;
    List<CouponShared> coupons;
    LayoutInflater inflater;

    public ReceiptViewAdapter(List<String> couponsTitles, Context ctx) {
        this.couponsTitles = couponsTitles;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ReceiptViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_coupon_receipt_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewAdapter.ViewHolder holder, int position) {
        String title = couponsTitles.get(position);
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setCouponShareds(List<CouponShared> couponShareds) {
        this.coupons = couponShareds;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.coupon_receipt_title_text_view);
        }
    }
}
