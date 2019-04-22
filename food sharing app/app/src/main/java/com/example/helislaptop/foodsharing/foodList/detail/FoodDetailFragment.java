package com.example.helislaptop.foodsharing.foodList.detail;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodBasicFragment;
import com.example.helislaptop.foodsharing.foodList.FoodItem;
import com.example.helislaptop.foodsharing.foodList.FoodItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends FoodBasicFragment {
    private static final String FOODITEM = "foodItem";
    private static int[] ICON_ARRAY = new int[]{R.drawable.post, R.drawable.request};
    private TextView descriptionView;
    private TextView timeView;
    private ImageView detailImage;
    private ImageView postImage;
    public static FoodDetailFragment newInstance(FoodItem foodItem) {
        Bundle args = new Bundle();
        args.putParcelable(FOODITEM, foodItem);
        FoodDetailFragment fragment = new FoodDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);

        descriptionView = view.findViewById(R.id.food_description);
        timeView = view.findViewById(R.id.food_time);
        detailImage = view.findViewById(R.id.food_detail_image);
        postImage = view.findViewById(R.id.post);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFoodItemDetail(getArguments().getParcelable(FOODITEM));
    }

    private void loadFoodItemDetail(FoodItem foodItem) {
        descriptionView.setText(foodItem.getDescription());
        timeView.setText(foodItem.getTime());
        detailImage.setImageResource(R.drawable.food_detail_sample);
        postImage.setImageResource(getDrawable(foodItem.getPostOrRequest()));
    }
    private @DrawableRes
    int getDrawable(String postOrRequest) {
        return postOrRequest.equals("Post")? ICON_ARRAY[1] : ICON_ARRAY[0];
    }
}
