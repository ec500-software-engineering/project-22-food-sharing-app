package com.example.helislaptop.foodsharing.foodList.foodPost;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helislaptop.foodsharing.FoodApplication;
import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodBasicFragment;
import com.example.helislaptop.foodsharing.database.AppDatabase;
import com.example.helislaptop.foodsharing.foodList.FoodItem;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPostFragment extends FoodBasicFragment {


    //Connect to database
    //find all required views, and bind with text view, new one FoodItem, and insert into database

    private final AppDatabase db = FoodApplication.getDataBase();
    private EditText ownerInput;
    private EditText descriptionInput;
    private EditText contactInput;
    private Button requestButton;
    private Button postButton;
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
        View view = inflater.inflate(R.layout.fragment_food_post, container, false);
        ownerInput = view.findViewById(R.id.owner_info);
        descriptionInput = view.findViewById(R.id.food_info);
        contactInput = view.findViewById(R.id.contact_info);
        requestButton = view.findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem foodItem = new FoodItem(ownerInput.getText().toString(), descriptionInput.getText().toString(),
                        "Request");
                addFoodItem(foodItem);
                //onBackPressed();
            }

        });
        postButton = view.findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem foodItem = new FoodItem(ownerInput.getText().toString(), descriptionInput.getText().toString(),
                        "Post");
                addFoodItem(foodItem);
                //onBackPressed();
            }

        });
        return view;
    }

    @SuppressLint("CheckResult")
    public void addFoodItem(FoodItem foodItem) {
        Completable.fromAction(() -> db.foodDao().insertFood(foodItem)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() ->{
        }, error -> {
        });
    }
}
