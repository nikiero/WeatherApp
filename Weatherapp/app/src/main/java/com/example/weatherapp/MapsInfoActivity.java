package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsInfoActivity extends AppCompatActivity {
    public static String DATA_KEY = "weatherData";

    private Button backButton;
    private TextView zonename;
    private TextView zonedescription;


    private void displayInfo(String response) throws JSONException {
        zonename = (TextView)findViewById(R.id.zonename);
        zonedescription = (TextView)findViewById(R.id.zonedescription);


        JSONObject root = new JSONObject(response);


        JSONObject main = root.getJSONObject("main");
        String temp = main.getString("temp");
        JSONObject weatherObject = root.getJSONArray("weather").getJSONObject(0);
        String weather = weatherObject.getString("main");
        String weatherDescription = weatherObject.getString("description");
        Log.i("response", response);

        String zoneName = root.getString("name");
        JSONObject zoneCountryObject = root.getJSONObject("sys");
        String zoneCountry = zoneCountryObject.getString("country");
        JSONObject windObject = root.getJSONObject("wind");
        String windSpeed = windObject.getString("speed");



        zonename.setText("Zone name is " + zoneCountry + ", " + zoneName);
        zonedescription.setText("Temp is " + temp + "°C" + "\n"
                + "Weather: "+ weather + ", " + weatherDescription + "\n"
                + "Speed wind: " + windSpeed);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                displayInfo(extras.getString(DATA_KEY));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}