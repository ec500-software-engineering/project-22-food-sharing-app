package com.example.helislaptop.foodsharing.foodList.foodPost;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helislaptop.foodsharing.FoodApplication;
import com.example.helislaptop.foodsharing.MainActivity;
import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.common.FoodBasicFragment;
import com.example.helislaptop.foodsharing.common.FoodFragmentManager;
import com.example.helislaptop.foodsharing.database.AppDatabase;
import com.example.helislaptop.foodsharing.foodList.FoodFragment;
import com.example.helislaptop.foodsharing.foodList.FoodItem;
import com.example.helislaptop.foodsharing.map.MapViewFragment;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPostFragment extends FoodBasicFragment{
    private TextView postionView;
    private LocationManager locationManager;
    private String locationProvider;
    private Location myLocation;

    //Connect to database
    //find all required views, and bind with text view, new one FoodItem, and insert into database

    private final AppDatabase db = FoodApplication.getDataBase();
    private EditText ownerInput;
    private EditText descriptionInput;
    private EditText phoneNumber;
    private EditText address;
    private EditText capacity;
    private EditText category;

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
        phoneNumber = view.findViewById(R.id.phone_number);
        address = view.findViewById(R.id.address_info);
        capacity = view.findViewById(R.id.capacity_info);
        category = view.findViewById(R.id.category_info);
        Location myCurrentLocation = getCurrentLocation();
        requestButton = view.findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ownerInput.getText() != null && ownerInput.getText().toString().length() != 0 && descriptionInput.getText() != null && descriptionInput.getText().toString().length() != 0) {
                    FoodItem foodItem = new FoodItem(ownerInput.getText().toString(), descriptionInput.getText().toString(),
                            "Request", phoneNumber.getText().toString(), address.getText().toString(), myLocation.getLongitude(), myLocation.getLatitude(), category.getText().toString(),capacity.getText().toString(),
                            "");
                    addFoodItem(foodItem);
                    //onBackPressed();
                    View tempView = LayoutInflater.from(getContext()).inflate(R.layout.toast_message, null);
                    TextView textView = tempView.findViewById(R.id.error);
                    textView.setText("    A request has been made.");

                    Toast toast = new Toast(getContext());
                    toast.setGravity(Gravity.CENTER, 0, 550);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(tempView);
                    toast.show();
                    foodFragmentManager.doFragmentTransaction(FoodFragment.newInstance());
                } else {
                    //Long s = new Date().getTime();
                    Toast errorToast = Toast.makeText(getContext(), "Please fill all required information!", Toast.LENGTH_LONG);
                    //Toast errorToast = Toast.makeText(getContext(),Double.toString(myLocation.getLatitude()),Toast.LENGTH_LONG);
                    errorToast.show();

                }
            }

        });
        postButton = view.findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ownerInput.getText() != null && ownerInput.getText().toString().length() != 0 && descriptionInput.getText() != null && descriptionInput.getText().toString().length() != 0) {


                    FoodItem foodItem = new FoodItem(ownerInput.getText().toString(), descriptionInput.getText().toString(),
                            "Post", "", "", myLocation.getLongitude(), myLocation.getLatitude(), "","","");

                    addFoodItem(foodItem);
                    //onBackPressed();
                    View tempView = LayoutInflater.from(getContext()).inflate(R.layout.toast_message, null);
                    TextView textView = tempView.findViewById(R.id.error);


                    Toast toast = new Toast(getContext());
                    toast.setGravity(Gravity.CENTER, 0, 550);
                    toast.setDuration(Toast.LENGTH_LONG);
                    textView.setText("    A post has been made.");
                    toast.setView(tempView);
                    toast.show();
                    MapViewFragment.addDataMap(foodItem);
                    foodFragmentManager.doFragmentTransaction(FoodFragment.newInstance());
                } else {

                    Toast errorToast = Toast.makeText(getContext(), "Please fill all required information!", Toast.LENGTH_LONG);
                    errorToast.show();
                }
            }

        });
        return view;
    }

    @SuppressLint("CheckResult")
    public void addFoodItem(FoodItem foodItem) {
        Completable.fromAction(() -> db.foodDao().insertFood(foodItem)).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
        }, error -> {
        });
    }


    public Location getCurrentLocation() {

        //get position from all possible sources
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        List<String> providers = locationManager.getProviders(false);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //if it's GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //if it's Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            myLocation = new Location("");
            double lon = -71.1027 + Math.random() * 0.1 - 0.05;
            double lat = 42.3471 + Math.random() * 0.1 - 0.05;
            myLocation.setLongitude(lon);
            myLocation.setLatitude(lat);
            Toast.makeText(getContext(), "Can't get Geo location", Toast.LENGTH_SHORT).show();

            return null;
        }
        //get Location
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if(location!=null){
            showLocation(location);
        }

        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
        return location;
    }

    private void showLocation(Location location){
        String locationStr = "Lat：" + location.getLatitude() +"\n"
                + "Lon：" + location.getLongitude();
        postionView.setText(locationStr);
    }

    LocationListener locationListener =  new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
    };

}
