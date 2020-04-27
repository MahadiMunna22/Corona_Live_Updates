package com.example.coronaliveupdates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Search_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView Country, T_T, T_D, T_R, T_I, N_D, N_I, N_T;
    Button submit;
    DatabaseReference dref;
    ProgressBar pbar;
    EditText EditCountry;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        Country = findViewById(R.id.Country);
        T_T = findViewById(R.id.Total_Tests);
        T_R = findViewById(R.id.Total_Recover);
        T_D = findViewById(R.id.Total_Death);
        T_I = findViewById(R.id.Total_Infected);
        N_D = findViewById(R.id.New_Death);
        N_I = findViewById(R.id.New_Infected);
        submit = findViewById(R.id.btn_submit);
        pbar = findViewById(R.id.progressBar);
        EditCountry = findViewById(R.id.editCountry);

        final String[] country = {"World"};

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(VISIBLE);
                if(EditCountry.getText().toString().length() == 0)
                    country[0] = "World";
                else
                    country[0] = EditCountry.getText().toString();

                dref = FirebaseDatabase.getInstance().getReference().child("Corona").child(country[0]);
                dref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("Total_Tests").exists()) {
                            T_T.setVisibility(VISIBLE);
                            T_R.setVisibility(VISIBLE);
                            T_D.setVisibility(VISIBLE);
                            T_I.setVisibility(VISIBLE);
                            N_D.setVisibility(VISIBLE);
                            N_I.setVisibility(VISIBLE);
                            String total_tests = dataSnapshot.child("Total_Tests").getValue().toString();
                            String total_recovered = dataSnapshot.child("Total_Recovered").getValue().toString();
                            String total_death = dataSnapshot.child("Total_Deaths").getValue().toString();
                            String total_infected = dataSnapshot.child("Total_Cases").getValue().toString();
                            String new_Infected = dataSnapshot.child("New_Cases").getValue().toString();
                            String new_death = dataSnapshot.child("New_Deaths").getValue().toString();
                            Country.setText(country[0]);
                            T_T.setText(total_tests);
                            T_R.setText(total_recovered);
                            T_D.setText(total_death);
                            T_I.setText(total_infected);
                            N_D.setText(new_death);
                            N_I.setText(new_Infected);
                        }
                        else {
                            Country.setText("Please Give correct Country name");
                            T_T.setVisibility(GONE);
                            T_R.setVisibility(GONE);
                            T_D.setVisibility(GONE);
                            T_I.setVisibility(GONE);
                            N_D.setVisibility(GONE);
                            N_I.setVisibility(GONE);
                        }
                        pbar.setVisibility(GONE);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


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
