package com.example.myapplication.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
        View view = inflater.inflate(R.layout.custom_category_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String category = categoriesNames.get(position);
        Integer image = categoriesImages.get(position);
        holder.title.setText(category);
        holder.image.setImageResource(image);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on " + category, Toast.LENGTH_SHORT).show();
//                Change the current View to the clicked category without change the activity
            NavController navController = Navigation.findNavController(v);
            switch (category) {

                case "אטרקציות":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(4L, "אטרקציות"));
                    break;
                case "הופעות":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(3L, "הופעות"));
                    break;
                case "מסעדות":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(5L, "מסעדות"));
                    break;
                case "ספא":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(1L, "ספא"));
                    break;
                case "ספורט":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(6L, "ספורט"));
                    break;
                case "שופינג":
                    navController.navigate(R.id.action_nav_home_to_nav_category, createCategoryBundle(2L, "שופינג"));
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesNames.size();
    }

    // Method to create a bundle with the category ID
    private Bundle createCategoryBundle(Long categoryId, String categoryName) {
        Bundle bundle = new Bundle();
        bundle.putLong("categoryId", categoryId);
        bundle.putString("categoryName", categoryName);
        return bundle;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            image = itemView.findViewById(R.id.imageView2);
        }
    }
}


