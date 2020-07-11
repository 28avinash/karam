package com.karam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.karam.rest.rest_messages.requests.Laborer;
import com.karam.view.activity.R;

import java.util.ArrayList;

/**
 * Adapter to get a list of laborers and their information
 */
public class LaborerListAdapter extends RecyclerView.Adapter<LaborerListAdapter.ViewHolder> {
    private ArrayList<Laborer> laborers;

    public LaborerListAdapter(ArrayList<Laborer> l, Context c) {
        this.laborers = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laborer_listview, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Fill laborer info to view holder based on the position to be filled
     * @param holder: View holder to be filled
     * @param position: Position of the view holder to be filled
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laborer l = this.laborers.get(position);

        holder.skills.setText(l.getSkills());
        holder.name.setText(l.getFname());
        holder.phoneNo.setText(l.getPhno());
        holder.preferred_location.setText(l.getPreferred_location());

    }

    /**
     * Get the number of laborers in the current list
     * @return Number of laborers in the current list
     */
    @Override
    public int getItemCount() {
        return this.laborers.size();
    }

    /**
     * Class that holds the view of a laborer
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentRelativeLayout;
        TextView name;
        TextView phoneNo;
        TextView preferred_location;
        TextView skills;

        public ViewHolder(View itemView) {
            super(itemView);

            parentRelativeLayout = itemView.findViewById(R.id.laborer_listview_relative_layout);
            name = itemView.findViewById(R.id.laborer_listview_name);
            phoneNo = itemView.findViewById(R.id.laborer_listview_phoneno);
            preferred_location = itemView.findViewById(R.id.laborer_listview_location);
            skills = itemView.findViewById(R.id.laborer_listview_skills);
        }
    }
}
