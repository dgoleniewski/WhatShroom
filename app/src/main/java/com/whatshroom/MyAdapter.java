package com.whatshroom;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<FavoriteLocation> locations;
    private Bundle savedInstanceState;

    public MyAdapter(Context context, List<FavoriteLocation> data, Bundle savedInstanceState) {
        this.context = context;
        this.locations = data;
        this.savedInstanceState = savedInstanceState;
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
        FavoriteLocation location = locations.get(position);
        holder.shroomLocationTextView.setText(location.getName());
        holder.descriptonTextView.setText(location.getDescription());
        holder.dateTextView.setText(location.getDate());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shroomLocationTextView, descriptonTextView, dateTextView;
        int position;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shroomLocationTextView = itemView.findViewById(R.id.shroomLocationTextView);
            descriptonTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            RemoveLocationDialogFragment dialogFragment = new RemoveLocationDialogFragment(context, position);
            dialogFragment.onCreateDialog(savedInstanceState).show();
        }
    }
}
