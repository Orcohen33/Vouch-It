package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.fragments.customer.category.CategoryViewModel;
import com.example.myapplication.models.coupon.CouponShared;

import java.util.List;


/**
 * A custom adapter to use with the RecyclerView widget.
 * This adapter is used to display a simple list of text
 * with a title and a subtitle and image.
 */
public class CustomerCouponsViewAdapter extends RecyclerView.Adapter<CustomerCouponsViewAdapter.ViewHolder> {
    LayoutInflater inflater;

    List<CouponShared> coupons;

    List<CategoryViewModel.ItemInCategory> items;
    static ItemClickListener itemClickListener;

    public CustomerCouponsViewAdapter(List<CategoryViewModel.ItemInCategory> coupons,ItemClickListener itemClickListener, Context ctx){
        this.items = coupons;
        this.itemClickListener = itemClickListener;
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
        CategoryViewModel.ItemInCategory item = items.get(position);
        holder.image.setImageResource(item.getImage());
        holder.title.setText(item.getTitle());
        holder.price.setText(String.format("%s₪", item.getPrice()));
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.list_anim2));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filterList(List<CategoryViewModel.ItemInCategory> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }

    public void updateList(List<CategoryViewModel.ItemInCategory> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView price;
        private ImageButton addToCart;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coupon_image_view);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            price = itemView.findViewById(R.id.coupon_price_text_view);
            addToCart = itemView.findViewById(R.id.add_to_cart_coupon_grid_layout);
            cardView = itemView.findViewById(R.id.eachCouponCustomer);
            addToCart.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onAddToCartClick(v, getAdapterPosition());
                }
            });
            image.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onImageClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void onAddToCartClick(View view, int position);
        void onImageClick(View view, int position);
    }
}


