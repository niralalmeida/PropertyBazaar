package com.example.rudolph.propertybazaar.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.models.Property;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rudolph Almeida on 3/12/2017.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private List<Property> properties;
    private int rowLayout;
    private Context context;

    public PropertyAdapter(List<Property> properties, int rowLayout, Context context) {
        this.properties = properties;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PropertyAdapter.PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {

        holder.title.setText(properties.get(position).getTitle());
        holder.garages.setText(Integer.toString(properties.get(position).getGarages()));
        holder.bedrooms.setText(Integer.toString(properties.get(position).getBedrooms()));
        holder.bathrooms.setText(Integer.toString(properties.get(position).getBathrooms()));

        Picasso.with(context).load(properties.get(position).getImage()).into(holder.cover);

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title;
        TextView bedrooms, bathrooms, garages;

        public PropertyViewHolder(View v) {
            super(v);
            cover = (ImageView) v.findViewById(R.id.iv_property_cover);
            title = (TextView) v.findViewById(R.id.tv_property_title);
            bedrooms = (TextView) v.findViewById(R.id.tv_property_bedrooms);
            bathrooms = (TextView) v.findViewById(R.id.tv_property_bathrooms);
            garages = (TextView) v.findViewById(R.id.tv_property_garages);
        }

    }

}
