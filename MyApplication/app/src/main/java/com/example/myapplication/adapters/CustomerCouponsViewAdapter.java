package com.example.myapplication.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.price.setText(item.getPrice() + "₪");

        // When the image button is clicked, show the description of the coupon.
        holder.image.setOnClickListener(v -> dialogForImageAction(position));
    }

    private void dialogForImageAction(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(inflater.getContext(), android.R.style.Theme_Light_Panel));
        builder.setTitle(items.get(position).getTitle());
        builder.setMessage(items.get(position).getDescription());
        builder.setIcon(items.get(position).getImage());
        builder.show();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    public void filterList(List<String> couponTitles, List<Integer> couponsImages, List<String> couponsPrices, String query) {
//        // filter the list of titles, images and prices according to the query
//        for (int i = 0; i < couponTitles.size(); i++) {
//            if (!couponTitles.get(i).toLowerCase().contains(query.toLowerCase())) {
//                couponTitles.remove(i);
//                couponsImages.remove(i);
//                couponsPrices.remove(i);
//                notifyItemRemoved(i);
//                notifyItemRangeChanged(i, couponTitles.size());
//            }
//        }
//    }
//
//    public void filterList(Map<Long, String> map, String query) {
//        List<String> filteredTitles = new ArrayList<>();
//        List<Long> filteredIds = new ArrayList<>();
//        for (Map.Entry<Long, String> entry : map.entrySet()) {
//            if (entry.getValue().toLowerCase().contains(query.toLowerCase())) {
//                filteredTitles.add(entry.getValue());
//                filteredIds.add(entry.getKey());
//            }
//        }
//        notifyDataSetChanged();
//        notifyItemRangeChanged(0, filteredTitles.size());
//    }

    public void filterList(List<CategoryViewModel.ItemInCategory> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }

    public void updateList(List<CategoryViewModel.ItemInCategory> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView price;
        ImageButton addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coupon_image_view);
            title = itemView.findViewById(R.id.coupon_title_text_view);
            price = itemView.findViewById(R.id.coupon_price_text_view);
            addToCart = itemView.findViewById(R.id.add_to_cart_coupon_grid_layout);
            addToCart.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onAddToCartClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void onAddToCartClick(View view, int position);
        void onImageClick(View view, int position);
    }
}


