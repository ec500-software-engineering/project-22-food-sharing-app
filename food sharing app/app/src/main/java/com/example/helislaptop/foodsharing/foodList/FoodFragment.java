package com.example.helislaptop.foodsharing.foodList;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helislaptop.foodsharing.ParseDatabase.FetchDataFromParse;
import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodBasicFragment;
import com.example.helislaptop.foodsharing.common.FoodFragmentManager;
import com.example.helislaptop.foodsharing.foodList.detail.FoodDetailFragment;
import com.example.helislaptop.foodsharing.foodList.foodPost.FoodPostFragment;
import com.example.helislaptop.foodsharing.mvp.FoodContract;
import com.example.helislaptop.foodsharing.mvp.FoodPresenter;
import com.example.helislaptop.foodsharing.mvp.MvpFragment;
import com.parse.ParseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends MvpFragment<FoodContract.Presenter> implements FoodContract.View, FoodContract.Model, FoodFragmentManager{


    private FoodItemAdapter foodItemAdapter;
    private TextView emptyState;
    public static FoodFragment newInstance() {

        FoodFragment fragment = new FoodFragment();
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //7.8
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyState = view.findViewById(R.id.empty_state);
        foodItemAdapter = new FoodItemAdapter(foodFragmentManager);
        recyclerView.setAdapter(foodItemAdapter);

        ImageView buttonView;
        buttonView = view.findViewById(R.id.add_button);
        buttonView.setImageResource(R.drawable.add);
        //ParseUser.logOutInBackground();
        //Log.i("user",ParseUser.getCurrentUser().toString());
        buttonView.setOnClickListener(v -> {
                if (ParseUser.getCurrentUser() != null) {
                    foodFragmentManager.doFragmentTransaction(FoodPostFragment.newInstance());
                } else {
                    Toast.makeText((Context) getContext(), "Please log in first!", Toast.LENGTH_LONG).show();
                }
            }
        );

        return view;
    }

    @Override
    public FoodContract.Presenter getPresenter() {
        return new FoodPresenter();
    }

    @Override
    public void loadFoodItems(List<FoodItem> foodItemList) {
        if (foodItemList.size() == 0) {
            emptyState.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.GONE);
        }
        if (foodItemList != null) {
            //Sorting here?
            foodItemAdapter.setFoodList(foodItemList);
        }

    }



    @Override
    public void fetchData() {

    }

    @Override
    public void addFoodItem(FoodItem foodItem) {

    }

    @Override
    public void deleteAllItem() {

    }

    @Override
    public void setPresenter(FoodContract.Presenter presenter) {

    }

    @Override
    public void doFragmentTransaction(FoodBasicFragment basicFragment) {

    }

    @Override
    public void startActivityWithBundle(Class<?> clazz, boolean isFinished, Bundle bundle) {

    }

    @Override
    public void showSnackBar(String message) {

    }

}
