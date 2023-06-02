package com.example.array;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class get_country_Activity extends AppCompatActivity implements PlaceSelectionListener {

    private static final int REQUEST_SELECT_PLACE = 1000;
    Button bt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_country);

//        "https://maps.googleapis.com/maps/api/place/textsearch/json?query=coffee+shop&location=35.792491,-78.653009&radius=2000&region=us&type=cafe,bakery&key=MY_API_KEY"
        bt = findViewById(R.id.bt);
        bt.setOnClickListener(v -> {
            try {

                GeoApiContext context = new GeoApiContext.Builder()
                        .apiKey("AIzaSyDUCMJaiX1dvLNq2aHSfe858eUQlNjfqgE")
                        .build();
                GeocodingResult[] results = new GeocodingResult[0];

                results = GeocodingApi.geocode(context,
                        "21.183982954930116, 72.79208492047947").await();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                System.out.println(gson.toJson(results[0].formattedAddress));
                String ad = results[0].formattedAddress;
                Log.d("full address", "onCreate: " + results[0].formattedAddress);
                String contry = results[0].formattedAddress;

                Geocoder gCoder = new Geocoder(this);
                List<Address> addresses = gCoder.getFromLocationName(contry, 2);

                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    String nameofc = address.getCountryName();
                    Log.d("countri_a", "onCreate: " + nameofc);
                }

////            addresses = contry;
//
//                Log.e("get adderess", addresses.get(0).getCountryName());
//            if (results[0].formattedAddress != null) {
////
//            String  ad2 = addresses.get(0).();
//                Log.d("full address", "onCreate: "+ad2);
//                System.out.print(addresses.get(0).getLocality());
//            }
            } catch (ApiException e) {
                Log.d("location_error", "onCreate: " + e);
//            throw new RuntimeException(e);
            } catch (InterruptedException e) {
                Log.d("location_error", "onCreate: " + e);

//            throw new RuntimeException(e);
            } catch (IOException e) {
                Log.d("location_error", "onCreate: " + e);

//            throw new RuntimeException(e);
            }


        });
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i("Selected", "Place Selected: 1 " + place.getAddress());

    }

    @Override
    public void onError(Status status) {
        Log.d("Selected error 2 ", "onCreate: 2 " + status);
    }
}