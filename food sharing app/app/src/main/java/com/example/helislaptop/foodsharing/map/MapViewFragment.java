package com.example.helislaptop.foodsharing.map;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helislaptop.foodsharing.FoodApplication;
import com.example.helislaptop.foodsharing.R;
import com.example.helislaptop.foodsharing.database.AppDatabase;
import com.example.helislaptop.foodsharing.database.FoodDao;
import com.example.helislaptop.foodsharing.foodList.FoodItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment {

    MapView mMapView;
    private final AppDatabase db = FoodApplication.getDataBase();
    private GoogleMap googleMap;

    public static List<FoodItem> foodItems;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button

                // For dropping a marker at a point on the Map
                LatLng boston1 = new LatLng(42.349319,-71.106722);
                googleMap.addMarker(new MarkerOptions().position(boston1).title("Photonic Center").snippet("Your Location"));
                // For zooming automatically to the location of the marker
                /*
                LatLng bostonFoodPost1 = new LatLng(42.34129,-71.128235);
                LatLng bostonFoodPost2 = new LatLng(42.331139, -71.099396);
                LatLng bostonFoodRequest1 = new LatLng(42.364125, -71.104202);
                googleMap.addMarker(new MarkerOptions().position(bostonFoodPost1).title("FoodPost1").snippet("Food for 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                googleMap.addMarker(new MarkerOptions().position(bostonFoodPost2).title("FoodPost2").snippet("Food for 3").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                googleMap.addMarker(new MarkerOptions().position(bostonFoodRequest1).title("FoodRequest1").snippet("Request for 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                   */



                for (FoodItem item : foodItems) {
                    //String itemName = Integer.toString(item.itemId);

                    LatLng  geoPoint = new LatLng(item.latitude,item.longitude);

                    if (item.postOrRequest.equals("Post")) {
                        googleMap.addMarker(new MarkerOptions().position(geoPoint).title(item.description).snippet("Food for 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    } else {
                        googleMap.addMarker(new MarkerOptions().position(geoPoint).title(item.description).snippet("Food request for 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    }

                }

                //googleMap.clear();
                CameraPosition cameraPosition = new CameraPosition.Builder().target(boston1).zoom(13).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @SuppressLint("CheckResult")
    public void fetchData() {
        db.foodDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(MapViewFragment::showDataMap, error -> System.out.println("error"), () -> {
                    System.out.println("complete");
                });
    }

    public static void showDataMap(List<FoodItem> foodItemList) {
        foodItems = new ArrayList<>();
        foodItems.addAll(foodItemList);
    }

}