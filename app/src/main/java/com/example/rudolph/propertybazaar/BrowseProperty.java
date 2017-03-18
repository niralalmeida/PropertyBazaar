package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.rudolph.propertybazaar.adapters.PropertyAdapter;
import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.PropertyResponse;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseProperty extends Activity {

    private BottomNavigationView bottomNavigationView;
    private ImageView internetError;
    private PropertyAdapter adapter;

    public static final String TAG = BrowseProperty.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_property);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bniv_bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        internetError = (ImageView) findViewById(R.id.iv_not_connected_internet);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_property_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);

        Call<PropertyResponse> call = apiService.getProperties();

        call.enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {
                int statusCode = response.code();
                List<Property> properties = response.body().getResults();
                internetError.setVisibility(View.INVISIBLE);
                adapter = new PropertyAdapter(properties, R.layout.propery_card, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                internetError.setVisibility(View.VISIBLE);
                Log.e(TAG, t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.browse_property_searchmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mi_filter_list:
                final Dialog dialog = new Dialog(this,android.R.style.Theme_Material_Light_DarkActionBar);
                dialog.setContentView(R.layout.filter_property_dialog);

                Spinner spinner = (Spinner) dialog.findViewById(R.id.spin_city);
                ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.city_array, android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

                dialog.setCancelable(true);
                dialog.setTitle("Property Constraints");
                Button filter = (Button) dialog.findViewById(R.id.b_filterButton);
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText bedrooms, bathrooms, garages, rooms, price, area;
                        Spinner city;

                        bedrooms = (EditText) dialog.findViewById(R.id.et_bedrooms);
                        bathrooms = (EditText) dialog.findViewById(R.id.et_bathrooms);
                        garages = (EditText) dialog.findViewById(R.id.et_garages);
                        rooms = (EditText) dialog.findViewById(R.id.et_rooms);
                        price = (EditText) dialog.findViewById(R.id.et_price);
                        area = (EditText) dialog.findViewById(R.id.et_area);
                        city = (Spinner) dialog.findViewById(R.id.spin_city);

                        Property constraint = new Property("", "", "",
                                city.getSelectedItem().toString(),
                                "",
                                !TextUtils.isEmpty(rooms.getText()) ? Integer.parseInt(rooms.getText().toString()) : 0,
                                !TextUtils.isEmpty(bathrooms.getText()) ? Integer.parseInt(bathrooms.getText().toString()) : 0,
                                !TextUtils.isEmpty(bedrooms.getText()) ? Integer.parseInt(bedrooms.getText().toString()) : 0,
                                !TextUtils.isEmpty(garages.getText()) ? Integer.parseInt(garages.getText().toString()) : 0,
                                !TextUtils.isEmpty(area.getText()) ? Integer.parseInt(area.getText().toString()) : 0,
                                "", "", "",
                                !TextUtils.isEmpty(price.getText()) ? Integer.parseInt(price.getText().toString()) : Integer.MAX_VALUE);

                        adapter.filterProperties(constraint);

                        dialog.dismiss();

                    }
                });

                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
