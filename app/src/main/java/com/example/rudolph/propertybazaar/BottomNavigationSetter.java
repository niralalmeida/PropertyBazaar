package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

/**
 * Created by rudol on 3/19/2017.
 */

public class BottomNavigationSetter {

    public static void setNavigationOnClickHandler(final Activity activity, BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    // TODO (2) Implement Navigation Bar
                    case R.id.action_add_new:
                        return true;

                    case R.id.action_browse:
                        return true;

                    case R.id.action_compare:
                        return true;

                    case R.id.action_profile:
                        return true;

                    default:
                        return true;

                }
            }
        });
    }

}
