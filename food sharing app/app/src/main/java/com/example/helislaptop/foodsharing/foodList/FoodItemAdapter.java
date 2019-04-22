package com.example.helislaptop.foodsharing.foodList;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodFragmentManager;
import com.example.helislaptop.foodsharing.foodList.detail.FoodDetailFragment;

import java.util.LinkedList;
import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodList;
    private FoodFragmentManager fragmentManager;
    private static int[] ICON_ARRAY = new int[]{R.drawable.post, R.drawable.request};


    public FoodItemAdapter(FoodFragmentManager foodFragmentManager) {
        this.fragmentManager = foodFragmentManager;
        this.foodList = new LinkedList<>();

    }

    public void setFoodList(List<FoodItem> foodList) {
        this.foodList.clear();
        this.foodList.addAll(foodList);
        notifyDataSetChanged();
    }

    @Override
    public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.owner.setText(foodItem.getOwner());
        holder.description.setText(foodItem.getDescription());
        holder.icon.setImageResource(getDrawable(foodItem.getPostOrRequest()));
        holder.itemView.setOnClickListener(v -> {
            fragmentManager.doFragmentTransaction(FoodDetailFragment.newInstance(foodItem));
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder {

        TextView owner;
        TextView description;
        ImageView icon;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            owner = itemView.findViewById(R.id.owner);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }
    private @DrawableRes
    int getDrawable(String postOrRequest) {
        return postOrRequest.equals("Post")? ICON_ARRAY[1] : ICON_ARRAY[0];
    }
}

