package layout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.adapters.ProfilePropertyAdapter;
import com.example.rudolph.propertybazaar.adapters.PropertyAdapter;
import com.example.rudolph.propertybazaar.models.Property;
import com.example.rudolph.propertybazaar.models.User;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfile extends Fragment {

    public UserProfile() {
        // Required empty public constructor
    }

    public static UserProfile newInstance() {
        return new UserProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">Profile</font>"));

        LinearLayout profile = (LinearLayout) view.findViewById(R.id.ll_user_profile_view);
        LinearLayout loginRegisterPage = (LinearLayout) view.findViewById(R.id.ll_login_register_view);

        final SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        boolean loggedIn = sharedPref.getBoolean("isLoggedIn", false);

        if (loggedIn) {
            profile.setVisibility(View.VISIBLE);
            loginRegisterPage.setVisibility(View.INVISIBLE);

            final TextView name = (TextView) view.findViewById(R.id.tv_user_full_name);
            final TextView email = (TextView) view.findViewById(R.id.tv_user_email);
            final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_property_list);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(10);
            recyclerView.setDrawingCacheEnabled(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<User> call = apiInterface.getUser(sharedPref.getString("username", null));

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    name.setText(response.body().getFirst_name() + " " + response.body().getLast_name());
                    email.setText(response.body().getEmail());
                    List<Property> properties = response.body().getProperties();
                    ProfilePropertyAdapter adapter = new ProfilePropertyAdapter(properties, R.layout.propery_card, getContext());
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

            CardView logout = (CardView) view.findViewById(R.id.b_logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("username");
                    editor.remove("token");
                    editor.putBoolean("isLoggedIn", false);
                    editor.commit();

                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

                    Fragment fragment = BrowseProperty.newInstance();
                    fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                }
            });

        } else {
            profile.setVisibility(View.INVISIBLE);
            loginRegisterPage.setVisibility(View.VISIBLE);

            FragmentManager fragmentManager = getActivity().getFragmentManager();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);


            CardView loginOpenButton = (CardView) view.findViewById(R.id.b_login_page_opener);
            loginOpenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = LoginUser.newInstance();
                    fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                }
            });

            CardView registerOpenButton = (CardView) view.findViewById(R.id.b_register_page_opener);
            registerOpenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = RegisterUser.newInstance();
                    fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                }
            });
        }

        return view;
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
