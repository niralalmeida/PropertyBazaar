package layout;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


    public class NewProperty extends Fragment {

        private int PICK_IMAGE_REQUEST = 1;
        Uri filePath;


    public NewProperty() {
        // Required empty public constructor
    }

    public static NewProperty newInstance() {
        return new NewProperty();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentview = inflater.inflate(R.layout.fragment_new_property, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">New Property</font>"));

        final SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        Spinner city_spinner = (Spinner) fragmentview.findViewById(R.id.spin_new_city);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.city_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_spinner.setAdapter(arrayAdapter);

        Spinner property_spinner = (Spinner) fragmentview.findViewById(R.id.spin_new_property_type);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.property_type_array, android.R.layout.simple_spinner_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        property_spinner.setAdapter(arrayAdapter2);

        CardView choose = (CardView) fragmentview.findViewById(R.id.b_choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        CardView submit = (CardView) fragmentview.findViewById(R.id.b_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
                    Toast.makeText(getContext(), "You need to login", Toast.LENGTH_SHORT).show();
                    return;
                }

                String authorization = "Token " + sharedPreferences.getString("token", null);
                String currentUserName = sharedPreferences.getString("username", null);

                EditText title = (EditText) fragmentview.findViewById(R.id.et_new_title);
                EditText description = (EditText) fragmentview.findViewById(R.id.et_new_description);
                EditText address = (EditText) fragmentview.findViewById(R.id.et_new_address);
                EditText price = (EditText) fragmentview.findViewById(R.id.et_new_price);
                EditText area = (EditText) fragmentview.findViewById(R.id.et_new_area);
                EditText garages = (EditText) fragmentview.findViewById(R.id.et_new_garages);
                EditText bedrooms = (EditText) fragmentview.findViewById(R.id.et_new_bedrooms);
                EditText bathrooms = (EditText) fragmentview.findViewById(R.id.et_new_bathrooms);
                EditText rooms = (EditText) fragmentview.findViewById(R.id.et_new_rooms);
                Spinner city = (Spinner) fragmentview.findViewById(R.id.spin_new_city);
                Spinner propertytype = (Spinner) fragmentview.findViewById(R.id.spin_new_property_type);
                String realFilePath = getRealPathFromURIPath(filePath, getContext());
                File file = new File(realFilePath);
                RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
                RequestBody titleBody = RequestBody.create(MediaType.parse("text/plain"), title.getText().toString());
                RequestBody descriptionBody = RequestBody.create(MediaType.parse("text/plain"), description.getText().toString());
                RequestBody addressBody = RequestBody.create(MediaType.parse("text/plain"), address.getText().toString());
                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), price.getText().toString());
                RequestBody areaBody = RequestBody.create(MediaType.parse("text/plain"), area.getText().toString());
                RequestBody garagesBody = RequestBody.create(MediaType.parse("text/plain"), garages.getText().toString());
                RequestBody bedroomsBody = RequestBody.create(MediaType.parse("text/plain"), bedrooms.getText().toString());
                RequestBody bathroomsBody = RequestBody.create(MediaType.parse("text/plain"), bathrooms.getText().toString());
                RequestBody roomsBody = RequestBody.create(MediaType.parse("text/plain"), rooms.getText().toString());
                RequestBody cityBody = RequestBody.create(MediaType.parse("text/plain"), city.getSelectedItem().toString());
                RequestBody propertyTypeBody = RequestBody.create(MediaType.parse("text/plain"), propertytype.getSelectedItem().toString());
                RequestBody owner = RequestBody.create(MediaType.parse("text/plain"), currentUserName);
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Property> call = apiInterface.uploadProperty(
                        authorization,
                        titleBody,
                        descriptionBody,
                        addressBody,
                        cityBody,
                        garagesBody,
                        bedroomsBody,
                        bathroomsBody,
                        roomsBody,
                        priceBody,
                        areaBody,
                        propertyTypeBody,
                        owner,
                        imageBody
                );

                call.enqueue(new Callback<Property>() {
                    @Override
                    public void onResponse(Call<Property> call, Response<Property> response) {
                        Toast.makeText(getContext(), "Property Uploaded", Toast.LENGTH_SHORT).show();
                        Fragment fragment = BrowseProperty.newInstance();
                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                    }

                    @Override
                    public void onFailure(Call<Property> call, Throwable t) {
                        Log.d("Upload Property", t.toString());
                    }
                });

            }
        });

        return fragmentview;
    }

    private String getRealPathFromURIPath(Uri contentURI, Context context) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(contentURI);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
