package com.whatshroom;

import com.google.android.gms.maps.model.LatLng;

public class FavoriteLocation {
    private LatLng latLng;
    private String name, description, date;

    public FavoriteLocation(LatLng latLng, String name, String description, String date) {
        this.latLng = latLng;
        this.name = name;
        this.description = description;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
