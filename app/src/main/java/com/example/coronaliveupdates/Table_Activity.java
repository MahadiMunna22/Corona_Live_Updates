package com.example.coronaliveupdates;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TableLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Table_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    BottomNavigationView navigationView;
    DatabaseReference dref;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_table);

        Log.d("TAG1","I came here");
        dref = FirebaseDatabase.getInstance().getReference().child("Corona");
        final CountryDataTable countryDataTable=new CountryDataTable((TableLayout) findViewById(R.id.table_id));
        countryDataTable.addTableHeaderContents(new String[]{"Country,\nOthers", "Total \nCases", "New \nCases","Total \nDeaths","New \nDeaths","Total \nRecovered","Active \nCases","Total \nTests"},8);

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String countryName = "World";
                try {
                    countryDataTable.addStringArrayContents(new String[]{countryName, dataSnapshot.child(countryName).child("Total_Cases").getValue().toString()
                            , dataSnapshot.child(countryName).child("New_Cases").getValue().toString(), dataSnapshot.child(countryName).child("Total_Deaths").getValue().toString()
                            , dataSnapshot.child(countryName).child("New_Deaths").getValue().toString(), dataSnapshot.child(countryName).child("Total_Recovered").getValue().toString()
                            , dataSnapshot.child(countryName).child("Active_Cases").getValue().toString(), dataSnapshot.child(countryName).child("Total_Tests").getValue().toString()}, 8);


                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    countryName = childSnapshot.getKey();
                    if(!countryName.equals("World")) {
                        countryDataTable.addStringArrayContents(new String[]{countryName, dataSnapshot.child(countryName).child("Total_Cases").getValue().toString()
                                , dataSnapshot.child(countryName).child("New_Cases").getValue().toString(), dataSnapshot.child(countryName).child("Total_Deaths").getValue().toString()
                                , dataSnapshot.child(countryName).child("New_Deaths").getValue().toString(), dataSnapshot.child(countryName).child("Total_Recovered").getValue().toString()
                                , dataSnapshot.child(countryName).child("Active_Cases").getValue().toString(), dataSnapshot.child(countryName).child("Total_Tests").getValue().toString()}, 8);
                    }
                }
                }
                catch (NullPointerException e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d("TAG1","I came here 2");

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_search);
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
                }

                return true;
            }
        });


        drawerLayout = findViewById(R.id.DrawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_home) {
            Intent i = new Intent(getApplicationContext(), Home_Activity.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.menu_map) {
            Intent i = new Intent(getApplicationContext(), Map_Activity.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.menu_coronatest) {
            Intent i = new Intent(getApplicationContext(), CoronaTest.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.menu_aboutus) {
            Intent i = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(i);
        }
        return false;
    }
}
