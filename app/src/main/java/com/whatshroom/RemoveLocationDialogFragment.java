package com.whatshroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoveLocationDialogFragment extends DialogFragment {
    private Context context;
    private int position;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private String locationsString;
    private List<FavoriteLocation> locations;


    public RemoveLocationDialogFragment(Context context, int position){
        this.context = context;
        this.position = position;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Czy na pewno chcesz usunąć?")
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        preferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
                        editor = preferences.edit();
                        gson = new Gson();
                        locationsString = preferences.getString("locations",null);
                        Type type = new TypeToken<ArrayList<FavoriteLocation>>(){}.getType();
                        locations = gson.fromJson(locationsString,type);
                        locations.remove(position);
                        locationsString = gson.toJson(locations);
                        editor.putString("locations", locationsString);
                        editor.apply();
                        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LocationsFragment()).commit();
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
