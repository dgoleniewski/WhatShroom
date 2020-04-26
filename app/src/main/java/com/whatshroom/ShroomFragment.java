package com.whatshroom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class ShroomFragment extends Fragment {
    static final int REQUEST_TAKE_PHOTO = 1;

    ShroomsClassifier shroomsClassifier;
    String currentPhotoPath;
    Pair<String, Float> shroom = new Pair<>("",-1f);
    File photoFile = null;
    Button photoButton;
    View view;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shroom_fragment,container,false);
        shroomsClassifier = new ShroomsClassifier(Objects.requireNonNull(getContext()));

        photoButton = view.findViewById(R.id.photoButton);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        return view;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(getContext()), "com.whatshroom.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + "_WhatShroom";
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(imageFileName,".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getPath());
            shroom = shroomsClassifier.predict(bitmap);
            bundle = new Bundle();
            bundle.putString("shroomName", shroom.first);
            bundle.putFloat("shroomProbability", shroom.second);
            ShroomInfoFragment shroomInfoFragment = new ShroomInfoFragment();
            shroomInfoFragment.setArguments(bundle);

            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, shroomInfoFragment).commit();

//            ImageView imageView = view.findViewById(R.id.shroomImageView);
//            imageView.setImageBitmap(bitmap);
        }
    }
}
