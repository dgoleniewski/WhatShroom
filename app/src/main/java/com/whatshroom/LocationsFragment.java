package com.whatshroom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;
import java.util.List;


public class LocationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<FavoriteLocation> locations;
    private View view;

    public LocationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.locations_fragment, container, false);
        locations = new LinkedList<>();
        locations.add(new FavoriteLocation(null,"Dodaj nową lokalizację", ""));
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getActivity(), locations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
