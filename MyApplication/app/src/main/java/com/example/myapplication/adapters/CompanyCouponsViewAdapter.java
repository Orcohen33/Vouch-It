package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CompanyCouponsViewAdapter extends RecyclerView.Adapter<CompanyCouponsViewAdapter.ViewHolder> {

    List<String> couponsTitles;

    LayoutInflater inflater;

    public CompanyCouponsViewAdapter(List<String> couponsTitles,
                                     Context ctx) {
        this.couponsTitles = couponsTitles;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_coupon_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyCouponsViewAdapter.ViewHolder holder, int position) {
        String title = couponsTitles.get(position);
        holder.title.setText(title);

        holder.delete.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on " + title, Toast.LENGTH_SHORT).show();

            // delete this item from the list
            couponsTitles.remove(position);
            // notify the adapter that the data has changed
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, couponsTitles.size());
        });
    }

    @Override
    public int getItemCount() {
        return couponsTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        Button delete;
        Button edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_coupon_text_view);
            delete = itemView.findViewById(R.id.delete_coupon_button);
            edit = itemView.findViewById(R.id.edit_coupon_button);
        }
    }
}
