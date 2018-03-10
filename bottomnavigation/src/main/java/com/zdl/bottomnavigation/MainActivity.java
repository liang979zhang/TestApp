package com.zdl.bottomnavigation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {


    AHBottomNavigation bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Label One", R.drawable.ic_content_add, Color.parseColor("#455C65"));
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Label Two", R.drawable.ic_maps_local_attraction, Color.parseColor("#455C65"));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Label Three", R.drawable.ic_maps_local_bar, Color.parseColor("#455C65"));

        // Add items
        bottom_navigation.addItem(item1);
        bottom_navigation.addItem(item2);
        bottom_navigation.addItem(item3);

        bottom_navigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Disable the translation inside the CoordinatorLayout
        bottom_navigation.setBehaviorTranslationEnabled(false);

// Change colors
        bottom_navigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottom_navigation.setInactiveColor(Color.parseColor("#747474"));

// Use colored navigation with circle reveal effect
        bottom_navigation.setColored(true);

// Set current item programmatically
        bottom_navigation.setCurrentItem(1);
    }
}
