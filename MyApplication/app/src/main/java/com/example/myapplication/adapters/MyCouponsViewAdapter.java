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
import java.util.Random;

public class MyCouponsViewAdapter extends RecyclerView.Adapter<MyCouponsViewAdapter.ViewHolder>{

    List<String> couponsTitles;
    List<String> couponsCode;
    List<String> couponsEndDate;
    List<Integer> couponsImage;
    LayoutInflater inflater;

    public MyCouponsViewAdapter(List<String> couponsTitles, List<String> couponsCode, List<String> couponsEndDate,
                                List<Integer> couponsImage, Context ctx){
        this.couponsTitles = couponsTitles;
        this.couponsCode = couponsCode;
        this.couponsEndDate = couponsEndDate;
        this.couponsImage = couponsImage;
        this.inflater = LayoutInflater.from(ctx);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_customer_mycoupons_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = couponsTitles.get(position);
        String endDate = couponsEndDate.get(position);
        Integer image = couponsImage.get(position);
        Random rand = new Random();
        long n = (long)(rand.nextDouble()*10000000000L) + 1000000000L;
        String code = "קוד קופון: "+n;

        holder.title.setText(title);
        holder.endDate.setText(endDate);
        holder.image.setImageResource(image);
        holder.code.setText(code);
    }

    @Override
    public int getItemCount() { return couponsTitles.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView code;
        TextView endDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coupon_image_view);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            endDate = itemView.findViewById(R.id.coupon_last_day_to_use_text_view);
            code = itemView.findViewById(R.id.coupon_code_text_view);
        }
    }
}
