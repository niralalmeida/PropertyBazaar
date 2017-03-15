package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
                recyclerView.setAdapter(new PropertyAdapter(properties, R.layout.propery_card, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                internetError.setVisibility(View.VISIBLE);
                Log.e(TAG, t.toString());
            }
        });

    }
}
