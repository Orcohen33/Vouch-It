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

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.ViewHolder> {
    List<String> categoriesNames;
    List<Integer> categoriesImages;
    LayoutInflater inflater;

    public CategoryViewAdapter(List<String> categoriesNames, List<Integer> categoriesImages, Context ctx) {
        this.categoriesNames = categoriesNames;
        this.categoriesImages = categoriesImages;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categoriesNames.get(position);
        holder.title.setText(category);
        holder.image.setImageResource(categoriesImages.get(position));
    }

    @Override
    public int getItemCount() {
        return categoriesNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.imageView2);
            image = itemView.findViewById(R.id.textView2);
        }

    }
}
