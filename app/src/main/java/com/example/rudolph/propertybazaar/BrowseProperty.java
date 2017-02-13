package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

public class BrowseProperty extends Activity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_property);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bniv_bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
    }
}
