package com.example.rudolph.propertybazaar.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.User;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;
import com.squareup.okhttp.Response;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rudolph Almeida on 3/12/2017.
 */

public class ProfilePropertyAdapter extends RecyclerView.Adapter<ProfilePropertyAdapter.ProfilePropertyViewHolder> {

    private List<Property> properties = new ArrayList<>();
    private int rowLayout;
    private Context context;
    private APIInterface apiservice;
    private int mExpandedPosition = -1;

    public ProfilePropertyAdapter(List<Property> properties, int rowLayout, Context context) {
        this.properties.addAll(properties);
        this.rowLayout = rowLayout;
        this.context = context;

        apiservice = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public ProfilePropertyAdapter.ProfilePropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProfilePropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProfilePropertyViewHolder holder, final int position) {

        final boolean isExpanded = (position == mExpandedPosition);
        holder.detailView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(holder.detailView);
                notifyDataSetChanged();
            }
        });

        holder.title.setText(properties.get(position).getTitle());
        holder.address.setText(properties.get(position).getAddress());
        holder.garages.setText(Integer.toString(properties.get(position).getGarages()));
        holder.bedrooms.setText(Integer.toString(properties.get(position).getBedrooms()));
        holder.bathrooms.setText(Integer.toString(properties.get(position).getBathrooms()));
        holder.price.setText(Integer.toString(properties.get(position).getPrice()));
        holder.rooms.setText(Integer.toString(properties.get(position).getRooms()));
        holder.description.setText(properties.get(position).getDescription());
        holder.area.setText(Integer.toString(properties.get(position).getArea()));

        holder.action1.setText("EDIT");
        holder.action2.setText("DELETE");

        final SharedPreferences sharedPreferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        final String authorization = "Token " + sharedPreferences.getString("token", null);

        holder.action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Response> call = apiInterface.deleteProperty(authorization, properties.get(position).getId());

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Snackbar.make(view, "Property Deleted", Snackbar.LENGTH_SHORT).show();
                        properties.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Snackbar.make(view, "Delete Failed", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final String username = properties.get(position).getOwner();

        Call<User> call = apiservice.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, final retrofit2.Response<User> response) {
                holder.owner_name.setText("Owner: " + response.body().getFirst_name() + " " + response.body().getLast_name());
                holder.owner_email.setText("Email: " + response.body().getEmail());

                holder.action1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO: Implement Action1 Listener

                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Property Adapter", t.toString());
            }
        });

        Picasso.with(context)
                .load(properties.get(position).getImage())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.placeholder)
                .fit().centerCrop()
                .into(holder.cover, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(properties.get(position).getImage())
                                .placeholder(R.drawable.placeholder)
                                .fit().centerCrop()
                                .into(holder.cover, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Toast.makeText(context, "Image Loading Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

    }

    @Override
    public int getItemCount() {
        return properties.size();
    }


    public static class ProfilePropertyViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title, address, description, owner_name, owner_email;
        TextView bedrooms, bathrooms, garages, rooms, price, area;
        Button action1, action2;
        LinearLayout detailView;

        public ProfilePropertyViewHolder(View v) {
            super(v);
            cover = (ImageView) v.findViewById(R.id.iv_property_cover);
            title = (TextView) v.findViewById(R.id.tv_property_title);
            address = (TextView) v.findViewById(R.id.tv_property_address);
            bedrooms = (TextView) v.findViewById(R.id.tv_property_bedrooms);
            bathrooms = (TextView) v.findViewById(R.id.tv_property_bathrooms);
            garages = (TextView) v.findViewById(R.id.tv_property_garages);
            rooms = (TextView) v.findViewById(R.id.tv_property_rooms);
            price = (TextView) v.findViewById(R.id.tv_property_price);
            description = (TextView) v.findViewById(R.id.tv_property_description);
            area = (TextView) v.findViewById(R.id.tv_property_area);
            owner_name = (TextView) v.findViewById(R.id.tv_owner_name);
            owner_email = (TextView) v.findViewById(R.id.tv_owner_email);
            detailView = (LinearLayout) v.findViewById(R.id.ll_detail_property_view);
            action1 = (Button) v.findViewById(R.id.b_action1);
            action2 = (Button) v.findViewById(R.id.b_action2);
        }

    }

}
