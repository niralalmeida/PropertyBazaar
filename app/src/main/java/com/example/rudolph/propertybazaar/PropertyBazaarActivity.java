package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import layout.BrowseProperty;

public class PropertyBazaarActivity extends Activity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_bazaar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bniv_bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        BottomNavigationSetter.setNavigationOnClickHandler(this, bottomNavigationView);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_property_bazaar, BrowseProperty.newInstance(), "PropertyList")
                    .commit();
        }

    }

}
