package com.whatshroom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<FavoriteLocation> locations;
    private FavoriteLocation location;

    public MyAdapter(Context context, List<FavoriteLocation> data) {
        this.context = context;
        this.locations = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.location_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        location = locations.get(position);
        holder.shroomLocationTextView.setText(location.getName());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shroomLocationTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shroomLocationTextView = itemView.findViewById(R.id.shroomLocationTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GoogleMapsFragment(true)).commit();
        }
    }
}
