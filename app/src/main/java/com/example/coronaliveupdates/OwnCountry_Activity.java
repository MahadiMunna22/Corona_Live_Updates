package com.example.coronaliveupdates;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;


public class OwnCountry_Activity extends MainActivity {
    private android.support.v7.widget.SearchView search;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_country_search);

        SearchView searchView = (SearchView) findViewById(R.id.SerachView);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_favourite);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                    overridePendingTransition(0,0);
                } else if (itemId == R.id.nav_favourite) {
                    startActivity(new Intent(getApplicationContext(), OwnCountry_Activity.class));
                    overridePendingTransition(0,0);
                } else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(getApplicationContext(), Search_Activity.class));
                    overridePendingTransition(0,0);
                }

                return true;
            }
        });
    }


}
