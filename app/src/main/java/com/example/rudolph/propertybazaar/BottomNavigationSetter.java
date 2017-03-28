package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import layout.BrowseProperty;
import layout.NewProperty;
import layout.UserProfile;

/**
 * Created by Rudolph Almeida on 3/19/2017.
 */

public class BottomNavigationSetter {

    public static void setNavigationOnClickHandler(final Activity activity, BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            private FragmentManager fragmentManager;
            private Fragment fragment;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                fragmentManager = activity.getFragmentManager();

                switch (item.getItemId()) {
                    case R.id.action_add_new:
                        fragment = NewProperty.newInstance();
                        break;

                    case R.id.action_browse:
                        fragment = BrowseProperty.newInstance();
                        break;

                    case R.id.action_profile:
                        fragment = UserProfile.newInstance();
                        break;

                    default:
                        return true;

                }

                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                fragmentTransaction.replace(R.id.activity_property_bazaar, fragment).commit();

                return true;
            }
        });
    }

}
