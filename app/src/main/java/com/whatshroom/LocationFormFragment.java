package com.whatshroom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.Calendar;

public class LocationFormFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private View view;
    private EditText titleEditText, descriptionEditText;
    private ImageButton dateImageButton;
    private TextView setDateTextView;
    private Button saveButton;
    private Context context;
    private LatLng latLng;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private String json;


    public LocationFormFragment(LatLng latLng) {
        this.latLng = latLng;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location_form_fragment, container, false);
        context = getContext();
        titleEditText = view.findViewById(R.id.titleEditText);
        descriptionEditText = view.findViewById(R.id.descriptionEditText);
        dateImageButton = view.findViewById(R.id.dateImageButton);
        setDateTextView = view.findViewById(R.id.setDateTextView);
        saveButton = view.findViewById(R.id.saveButton);

        dateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteLocation location = new FavoriteLocation(
                        latLng,
                        titleEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        setDateTextView.getText().toString()
                );
                preferences = context.getSharedPreferences("sharedPreferences",Context.MODE_PRIVATE);
                editor = preferences.edit();
                gson = new Gson();
                json = gson.toJson();
            }
        });

        return view;
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        setDateTextView.setText(date);
    }
}
