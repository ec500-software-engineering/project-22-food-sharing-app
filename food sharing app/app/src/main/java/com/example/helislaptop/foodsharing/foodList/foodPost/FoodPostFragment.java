package com.example.helislaptop.foodsharing.foodList.foodPost;


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
public class FoodPostFragment extends FoodBasicFragment {


    public static FoodPostFragment newInstance() {

        Bundle args = new Bundle();

        FoodPostFragment fragment = new FoodPostFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_post, container, false);
    }

}
