package com.example.myapplication.adapters;

import android.content.Context;
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
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
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
            /*
                TODO: 1.Change the names of the cateogries to the name of the fragment
                      2.Change the "R.id.*" to the name of the fragment
             */
            if (category.equals("Category 1")) {
                navController.navigate(R.id.nav_gallery);
            } else if (category.equals("Category 2")) {
                navController.navigate(R.id.nav_slideshow);
            } else if (category.equals("Category 3")) {
                navController.navigate(R.id.nav_slideshow);
            } else if (category.equals("Category 4")) {
                navController.navigate(R.id.nav_slideshow);
            } else if (category.equals("Category 5")) {
                navController.navigate(R.id.nav_slideshow);
            }
            // then reset the current fragment to the first item
             navController.popBackStack(R.id.nav_home, true);

        });
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
            title = itemView.findViewById(R.id.textView2);
            image = itemView.findViewById(R.id.imageView2);
        }
    }
}
