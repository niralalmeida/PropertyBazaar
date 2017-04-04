package layout;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.models.AuthenticationToken;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginUser extends Fragment {



    public LoginUser() {
        // Required empty public constructor
    }

    public static LoginUser newInstance() {
        return new LoginUser();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_user, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">Login</font>"));

        final EditText username = (EditText) view.findViewById(R.id.et_username_login);
        final EditText password = (EditText) view.findViewById(R.id.et_password_login);

        CardView login = (CardView) view.findViewById(R.id.b_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText())) {
                    Toast.makeText(getContext(), "Username is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getContext(), "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

                Call<AuthenticationToken> call = apiInterface.getToken(username.getText().toString(), password.getText().toString());

                call.enqueue(new Callback<AuthenticationToken>() {
                    @Override
                    public void onResponse(Call<AuthenticationToken> call, Response<AuthenticationToken> response) {
                        String authToken = response.body().getToken();

                        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("username", username.getText().toString());
                        editor.putString("token", authToken);
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        Fragment fragment = UserProfile.newInstance();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                        fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                    }

                    @Override
                    public void onFailure(Call<AuthenticationToken> call, Throwable t) {
                        Toast.makeText(getContext(), "Invalid Username/Password combo", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return view;

    }

}
