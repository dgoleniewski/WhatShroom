package com.whatshroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class GoogleMapsFragment extends Fragment {
    private boolean newLocation;
    private ImageButton addLocationImageButton;
    private View view;
    private LatLng markerLatLng;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private Gson gson;
    private String locationsString;
    private List<FavoriteLocation> locations;


    public GoogleMapsFragment(){
        this.newLocation = false;
    }
    public GoogleMapsFragment(boolean newLocation){
        this.newLocation = newLocation;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(final GoogleMap googleMap) {
            googleMap.setMyLocationEnabled(true);
            if(newLocation){
                googleMap.clear();
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(latLng));
                        markerLatLng = latLng;
                        addLocationImageButton.setVisibility(View.VISIBLE);
                    }
                });
            }
            else {
                sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                gson = new Gson();
                locationsString = sharedPreferences.getString("locations",null);
                Type type = new TypeToken<ArrayList<FavoriteLocation>>(){}.getType();
                locations = gson.fromJson(locationsString,type);

                for (FavoriteLocation location :
                        locations) {
                    googleMap.addMarker(new MarkerOptions().position(location.getLatLng()).title(location.getName()));
                }
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.google_maps_fragment, container, false);
        context = getContext();
        addLocationImageButton = view.findViewById(R.id.addLocationImageButton);
        addLocationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LocationFormFragment(markerLatLng)).commit();

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
