package com.example.helislaptop.foodsharing.foodList.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodBasicFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends FoodBasicFragment {


    public static FoodDetailFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FoodDetailFragment fragment = new FoodDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

}
