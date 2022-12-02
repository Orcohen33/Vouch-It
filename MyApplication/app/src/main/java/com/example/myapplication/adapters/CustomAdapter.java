package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    List<String> categories;

    public CustomAdapter(Context context, List<String> categories) {
        this.context = context;
        this.categories = categories;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_raw_view, parent, false);
        System.out.println("onCreateViewHolder called");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println("onBindViewHolder called");
        holder.categoryTextView.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("MyViewHolder called");
            categoryTextView = itemView.findViewById(R.id.category_text_view);
        }
    }

    public void addNewCategory(String category) {
        categories.add(category);
        System.out.println("addNewCategory called");
    }
}
