package layout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rudolph.propertybazaar.R;
import com.example.rudolph.propertybazaar.models.User;
import com.example.rudolph.propertybazaar.rest.APIClient;
import com.example.rudolph.propertybazaar.rest.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterUser extends Fragment {

    public RegisterUser() {
        // Required empty public constructor
    }

    public static RegisterUser newInstance() {
        return new RegisterUser();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_user, container, false);

        getActivity().getActionBar().setTitle(Html.fromHtml("<font color=\"#ffffff\">Register</font>"));

        final EditText username = (EditText) view.findViewById(R.id.et_register_username);
        final EditText email = (EditText) view.findViewById(R.id.et_register_email);
        final EditText password = (EditText) view.findViewById(R.id.et_register_password);
        final EditText password2 = (EditText) view.findViewById(R.id.et_register_password_again);
        final EditText firstName  = (EditText) view.findViewById(R.id.et_register_first_name);
        final EditText lastName = (EditText) view.findViewById(R.id.et_register_last_name);
        CardView register = (CardView) view.findViewById(R.id.b_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText())) {
                    Toast.makeText(getContext(), "Username is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(firstName.getText())) {
                    Toast.makeText(getContext(), "First Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(lastName.getText())) {
                    Toast.makeText(getContext(), "Last Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getContext(), "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password2.getText())) {
                    Toast.makeText(getContext(), "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email.getText())) {
                    Toast.makeText(getContext(), "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.equals(password.getText(), password2.getText())) {
                    Toast.makeText(getContext(), "Both Passwords should match", Toast.LENGTH_SHORT).show();
                    return;
                }

                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<User> call = apiInterface.registerUser(
                        username.getText().toString(),
                        password.getText().toString(),
                        lastName.getText().toString(),
                        firstName.getText().toString(),
                        email.getText().toString()
                );

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(getContext(), "User Creation Succesful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getActivity().getFragmentManager();
                        Fragment fragment = LoginUser.newInstance();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                        fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(), "User Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

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
