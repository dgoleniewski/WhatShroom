package com.whatshroom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoogleMapsFragment extends Fragment {
    private boolean newLocation;
    private ImageButton addLocationImageButton;
    private LatLng markerLatLng;
    private Context context;

    public GoogleMapsFragment(){
        this.newLocation = false;
    }
    public GoogleMapsFragment(boolean newLocation){
        this.newLocation = newLocation;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
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
                SharedPreferences sharedPreferences = context.getSharedPreferences
                        ("sharedPreferences", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String locationsString = sharedPreferences.getString("locations", null);
                Type type = new TypeToken<ArrayList<FavoriteLocation>>(){}.getType();
                List<FavoriteLocation> locations = gson.fromJson(locationsString, type);
                if(locations != null){
                    for (FavoriteLocation location : locations) {
                        googleMap.addMarker(new MarkerOptions().
                                position(location.getLatLng()).
                                title(location.getName()));
                    }
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_maps_fragment, container, false);
        context = getContext();
        addLocationImageButton = view.findViewById(R.id.addLocationImageButton);
        addLocationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) Objects.requireNonNull(getContext())).
                        getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragmentContainer, new LocationFormFragment(markerLatLng)).
                        commit();
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
