package com.whatshroom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class LocationsFragment extends Fragment{
    private RecyclerView recyclerView;
    private ImageButton newLocationImageButton;
    private MyAdapter adapter;
    private List<FavoriteLocation> locations;
    private View view;
    private SharedPreferences preferences;
    private Context context;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private String locationsString;

    public LocationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.locations_fragment, container, false);
        newLocationImageButton = view.findViewById(R.id.newLocationImageButton);
        newLocationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GoogleMapsFragment(true)).commit();
            }
        });
        context = getContext();
        locations = new LinkedList<>();
        preferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();
        locationsString = preferences.getString("locations",null);
        Type type = new TypeToken<ArrayList<FavoriteLocation>>(){}.getType();
        locations = gson.fromJson(locationsString,type);
        if(locations==null)
            locations = new LinkedList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getActivity(), locations, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
