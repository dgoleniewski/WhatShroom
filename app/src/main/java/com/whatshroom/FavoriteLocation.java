package com.whatshroom;

import com.google.android.gms.maps.model.LatLng;

public class FavoriteLocation {
    private LatLng latLng;
    private String name;

    public FavoriteLocation(LatLng latLng, String name) {
        this.latLng = latLng;
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
