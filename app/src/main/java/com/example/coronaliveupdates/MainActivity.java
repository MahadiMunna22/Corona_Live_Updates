package com.example.coronaliveupdates;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener{
    private android.support.v7.widget.SearchView search;
    BottomNavigationView navigationView;
    ListView list;
    ListViewAdapter adapter;
    android.widget.SearchView editsearch;
    String[] countryList;
    ArrayList<CountryNames> arraylist = new ArrayList<CountryNames>();
    String CountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int country = 0;
        String val = "0";
        try {
            Intent intent = getIntent();
            val = intent.getStringExtra("CountrySelected");
            Log.d("Val1", val);
        }
        catch (Exception e){
            Log.d("Val1","Starting");
            val = "0";
        }
        country = Integer.parseInt(val);
        int CountryData;
        try{
            CountryData = readFromFile(getApplicationContext()).length();
        }
        catch(Exception e){
            CountryData = 0;
        }

        if(country == 0 &&  CountryData!= 0){
            //startActivity(new Intent(getApplicationContext(),Home_Activity.class));
            Intent i = new Intent(MainActivity.this, OwnCountry_Activity.class);
            startActivity(i);
            overridePendingTransition(0,0);
        }
        else {

            countryList = new String[]{
                    "Afghanistan",
                    "Albania",
                    "Algeria",
                    "Andorra",
                    "Angola",
                    "Anguilla",
                    "Antigua_and_Barbuda",
                    "Argentina",
                    "Armenia",
                    "Aruba",
                    "Australia",
                    "Austria",
                    "Azerbaijan",
                    "Bahamas",
                    "Bahrain",
                    "Bangladesh",
                    "Barbados",
                    "Belarus",
                    "Belgium",
                    "Belize",
                    "Benin",
                    "Bermuda",
                    "Bhutan",
                    "Bolivia",
                    "Bosnia_and_Herzegovina",
                    "Botswana",
                    "Brazil",
                    "British_Virgin_Islands",
                    "Brunei_",
                    "Bulgaria",
                    "Burkina_Faso",
                    "Burundi",
                    "Cabo_Verde",
                    "Cambodia",
                    "Cameroon",
                    "Canada",
                    "CAR",
                    "Caribbean_Netherlands",
                    "Cayman_Islands",
                    "Chad",
                    "Channel_Islands",
                    "Chile",
                    "China",
                    "Colombia",
                    "Congo",
                    "Costa_Rica",
                    "Croatia",
                    "Cuba",
                    "Curaçao",
                    "Cyprus",
                    "Czechia",
                    "Denmark",
                    "Djibouti",
                    "Dominica",
                    "Dominican_Republic",
                    "DRC",
                    "Ecuador",
                    "Egypt",
                    "El_Salvador",
                    "Equatorial_Guinea",
                    "Eritrea",
                    "Estonia",
                    "Eswatini",
                    "Ethiopia",
                    "Faeroe_Islands",
                    "Falkland_Islands",
                    "Fiji",
                    "Finland",
                    "France",
                    "French_Guiana",
                    "French_Polynesia",
                    "Gabon",
                    "Gambia",
                    "Georgia",
                    "Germany",
                    "Ghana",
                    "Gibraltar",
                    "Greece",
                    "Greenland",
                    "Grenada",
                    "Guadeloupe",
                    "Guatemala",
                    "Guinea",
                    "Guinea-Bissau",
                    "Guyana",
                    "Haiti",
                    "Honduras",
                    "Hong_Kong",
                    "Hungary",
                    "Iceland",
                    "India",
                    "Indonesia",
                    "Iran",
                    "Iraq",
                    "Ireland",
                    "Isle_of_Man",
                    "Israel",
                    "Italy",
                    "Ivory_Coast",
                    "Jamaica",
                    "Japan",
                    "Jordan",
                    "Kazakhstan",
                    "Kenya",
                    "Kuwait",
                    "Kyrgyzstan",
                    "Laos",
                    "Latvia",
                    "Lebanon",
                    "Liberia",
                    "Libya",
                    "Liechtenstein",
                    "Lithuania",
                    "Luxembourg",
                    "Macao",
                    "Madagascar",
                    "Malawi",
                    "Malaysia",
                    "Maldives",
                    "Mali",
                    "Malta",
                    "Martinique",
                    "Mauritania",
                    "Mauritius",
                    "Mayotte",
                    "Mexico",
                    "Moldova",
                    "Monaco",
                    "Mongolia",
                    "Montenegro",
                    "Montserrat",
                    "Morocco",
                    "Mozambique",
                    "Myanmar",
                    "Namibia",
                    "Nepal",
                    "Netherlands",
                    "New_Caledonia",
                    "New_Zealand",
                    "Nicaragua",
                    "Niger",
                    "Nigeria",
                    "North_Macedonia",
                    "Norway",
                    "Oman",
                    "Pakistan",
                    "Palestine",
                    "Panama",
                    "Papua_New_Guinea",
                    "Paraguay",
                    "Peru",
                    "Philippines",
                    "Poland",
                    "Portugal",
                    "Qatar",
                    "Romania",
                    "Russia",
                    "Rwanda",
                    "Réunion",
                    "S_Korea",
                    "Saint_Kitts_and_Nevis",
                    "Saint_Lucia",
                    "Saint_Martin",
                    "Saint_Pierre_Miquelon",
                    "San_Marino",
                    "Sao_Tome_and_Principe",
                    "Saudi_Arabia",
                    "Senegal",
                    "Serbia",
                    "Seychelles",
                    "Sierra_Leone",
                    "Singapore",
                    "Sint_Maarten",
                    "Slovakia",
                    "Slovenia",
                    "Somalia",
                    "South_Africa",
                    "South_Sudan",
                    "Spain",
                    "Sri_Lanka",
                    "St_Barth",
                    "St_Vincent_Grenadines",
                    "Sudan",
                    "Suriname",
                    "Sweden",
                    "Switzerland",
                    "Syria",
                    "Taiwan",
                    "Tanzania",
                    "Thailand",
                    "Timor-Leste",
                    "Togo",
                    "Trinidad_and_Tobago",
                    "Tunisia",
                    "Turkey",
                    "Turks_and_Caicos",
                    "UAE",
                    "Uganda",
                    "UK",
                    "Ukraine",
                    "Uruguay",
                    "USA",
                    "Uzbekistan",
                    "Vatican_City",
                    "Venezuela",
                    "Vietnam",
                    "Western_Sahara",
                    "Yemen",
                    "Zambia",
                    "Zimbabwe"
            };

            // Locate the ListView in listview_main.xml
            list = (ListView) findViewById(R.id.listview);

            for (int i = 0; i < countryList.length; i++) {
                CountryNames countryNames = new CountryNames(countryList[i]);
                // Binds all strings into an array
                arraylist.add(countryNames);
            }

            // Pass results to ListViewAdapter Class
            adapter = new ListViewAdapter(this, arraylist);

            // Binds the Adapter to the ListView
            list.setAdapter(adapter);

            // Locate the EditText in listview_main.xml
            editsearch = (android.widget.SearchView) findViewById(R.id.SearchView);
            editsearch.setOnQueryTextListener(this);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CountryName = adapter.getItem(position).getCountryName();
                    writeToFile(CountryName, getApplicationContext());
                    Toast.makeText(getApplicationContext(),readFromFile(getApplicationContext()),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, OwnCountry_Activity.class));
                }
            });

        }

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("SelectCountry.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Log.d("FileDir","Saved to " + getFilesDir() + "/" + "SelectCountry.txt");
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";
//        File file = new File("/data/user/0/com.example.coronaliveupdates/files/SelectCountry.txt");
//        Scanner input = null;
//        try {
//            input = new Scanner(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }



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
