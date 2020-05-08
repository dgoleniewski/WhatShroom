package com.whatshroom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LocationsFragment extends Fragment{
    private Context context;

    public LocationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.locations_fragment, container, false);
        ImageButton newLocationImageButton = view.findViewById(R.id.newLocationImageButton);
        newLocationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GoogleMapsFragment(true)).commit();
            }
        });
        context = getContext();
        List<FavoriteLocation> locations = getLocationsFromSharedPreferences();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(getActivity(), locations, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private List<FavoriteLocation> getLocationsFromSharedPreferences(){
        SharedPreferences preferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String locationsString = preferences.getString("locations", null);
        Type type = new TypeToken<ArrayList<FavoriteLocation>>(){}.getType();
        List<FavoriteLocation> loc = gson.fromJson(locationsString, type);
        if(loc == null){
            return new LinkedList<>();
        }
        return loc;
    }
}