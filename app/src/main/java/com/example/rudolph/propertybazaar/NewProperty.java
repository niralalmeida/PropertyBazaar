package com.example.rudolph.propertybazaar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

public class NewProperty extends Activity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_property);

        this.getActionBar().setTitle("Add New Property");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bniv_bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        BottomNavigationSetter.setNavigationOnClickHandler(this, bottomNavigationView);

    }
}
