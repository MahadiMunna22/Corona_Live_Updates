package com.example.coronaliveupdates;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class OwnCountry_Activity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    TextView Country, T_T, T_D, T_R, T_I, N_D, N_I, N_T;
    DatabaseReference dref;
    ProgressBar pbar;
    Button btn_changeCountry;
    LinearLayout linearLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_own_country_search);

        Country = findViewById(R.id.Country);
        T_T = findViewById(R.id.Total_Tests);
        T_R = findViewById(R.id.Total_Recover);
        T_D = findViewById(R.id.Total_Death);
        T_I = findViewById(R.id.Total_Infected);
        N_D = findViewById(R.id.New_Death);
        N_I = findViewById(R.id.New_Infected);
        pbar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.LinearLayout);


        final String[] country = {"World"};

        country[0] = readFromFile(getApplicationContext());
        pbar.setVisibility(VISIBLE);
        linearLayout.setVisibility(GONE);

        dref = FirebaseDatabase.getInstance().getReference().child("Corona").child(country[0]);
        dref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                T_T.setVisibility(VISIBLE);
                T_R.setVisibility(VISIBLE);
                T_D.setVisibility(VISIBLE);
                T_I.setVisibility(VISIBLE);
                N_D.setVisibility(VISIBLE);
                N_I.setVisibility(VISIBLE);
                try {
                    String total_tests = (dataSnapshot.child("Total_Tests").getValue()).toString();
                    String total_recovered = (dataSnapshot.child("Total_Recovered").getValue()).toString();
                    String total_death = (dataSnapshot.child("Total_Deaths").getValue()).toString();
                    String total_infected = (dataSnapshot.child("Total_Cases").getValue()).toString();
                    String new_Infected = (dataSnapshot.child("New_Cases").getValue()).toString();
                    String new_death = (dataSnapshot.child("New_Deaths").getValue()).toString();
                    Country.setText(country[0]);
                    T_T.setText(total_tests);
                    T_R.setText(total_recovered);
                    T_D.setText(total_death);
                    T_I.setText(total_infected);
                    N_D.setText(new_death);
                    N_I.setText(new_Infected);
                    pbar.setVisibility(GONE);
                    linearLayout.setVisibility(VISIBLE);
                }
                catch (NullPointerException e){
                    Log.d("Tag1","Null Pointer Exception");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.nav_favourite);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                    overridePendingTransition(0,0);
                }
                else if (itemId == R.id.nav_search) {
                    startActivity(new Intent(getApplicationContext(), Search_Activity.class));
                    overridePendingTransition(0,0);
                }

                return true;
            }
        });

        btn_changeCountry = findViewById(R.id.Btn_changeCountry);
        btn_changeCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("CountrySelected","1");
                startActivity(i);
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
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("SelectCountry.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret.substring(1);
    }


}
