package com.example.weatherapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

public class OpenWeatherService {
    public OpenWeatherService(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    private final RequestQueue queue;

    void collectWeather(LatLng latLng, Response.Listener<java.lang.String> listener, Response.ErrorListener errorListener) {
        String API_KEY = "a18a2770f9d064c5e5d485e6a8a82c86";
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&lang=en&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(url, latLng.latitude, latLng.longitude, API_KEY), listener, errorListener);
        queue.add(stringRequest);
    }
}
