package layout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.adapters.PropertyAdapter;
import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.PropertyResponse;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrowseProperty extends Fragment {

    private ImageView internetError;
    private PropertyAdapter adapter = null;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    public BrowseProperty() {
        // Required empty public constructor
    }

    public static BrowseProperty newInstance() {
        return new BrowseProperty();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_browse_property, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">Browse Property</font>"));

        internetError = (ImageView) view.findViewById(R.id.iv_not_connected_internet);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_filter_property);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Material_Light);
                dialog.setContentView(R.layout.filter_property_dialog);

                Spinner spinner = (Spinner) dialog.findViewById(R.id.spin_city);
                ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.filterable_city_array, android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);

                Spinner property_spinner = (Spinner) dialog.findViewById(R.id.spin_property_type);
                ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.filterable_property_type_array, android.R.layout.simple_spinner_item);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                property_spinner.setAdapter(arrayAdapter2);

                dialog.setCancelable(true);
                dialog.setTitle("Filter Properties");
                Button filter = (Button) dialog.findViewById(R.id.b_filterButton);
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (adapter == null) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Nothing to filter", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        EditText bedrooms, bathrooms, garages, rooms, price, area;
                        Spinner city, propertyType;

                        bedrooms = (EditText) dialog.findViewById(R.id.et_bedrooms);
                        bathrooms = (EditText) dialog.findViewById(R.id.et_bathrooms);
                        garages = (EditText) dialog.findViewById(R.id.et_garages);
                        rooms = (EditText) dialog.findViewById(R.id.et_rooms);
                        price = (EditText) dialog.findViewById(R.id.et_price);
                        area = (EditText) dialog.findViewById(R.id.et_area);
                        city = (Spinner) dialog.findViewById(R.id.spin_city);
                        propertyType = (Spinner) dialog.findViewById(R.id.spin_property_type);

                        Property constraint = new Property("", "", "",
                                city.getSelectedItem().toString(),
                                propertyType.getSelectedItem().toString(),
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
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_property_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        APIInterface apiService = APIClient.getClient().create(APIInterface.class);

        Call<PropertyResponse> call = apiService.getProperties();

        call.enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {
                List<Property> properties = response.body().getResults();
                internetError.setVisibility(View.GONE);
                adapter = new PropertyAdapter(properties, R.layout.propery_card, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                internetError.setVisibility(View.VISIBLE);
                Log.e(BrowseProperty.class.getSimpleName(), t.toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
