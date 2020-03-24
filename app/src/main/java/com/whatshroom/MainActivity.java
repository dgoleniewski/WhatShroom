package com.whatshroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.mapboxsdk.Mapbox;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "key");
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ShroomFragment()).commit();
        }
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

        bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.shroomItem:
                        fragment = new ShroomFragment();
                        break;
                    case R.id.mapItem:
                        fragment = new MapFragment();
                        break;
                    case R.id.settingsItem:
                        fragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });

    }
}
