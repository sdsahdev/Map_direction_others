package com.example.array.LocationSugge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.array.R;
import com.example.array.adapter.PlaceAutoSuggestAdapter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class addressActivity extends AppCompatActivity {

//    AutoCompleteTextView edit_text;
//    TextView text_view1, text_view2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
//        edit_text = findViewById(R.id.edit_text);
//        text_view1 = findViewById(R.id.text_view1);
//        text_view2 = findViewById(R.id.text_view2);


        Places.initialize(getApplicationContext(), "AIzaSyDUCMJaiX1dvLNq2aHSfe858eUQlNjfqgE");
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);

        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(addressActivity.this, android.R.layout.simple_list_item_1));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ", autoCompleteTextView.getText().toString());
                LatLng latLng = getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if (latLng != null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address = getAddressFromLatLng(latLng);
                    if (address != null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ", "" + address.getAddressLine(0));
                        Log.d("Phone : ", "" + address.getPhone());
                        Log.d("Pin Code : ", "" + address.getPostalCode());
                        Log.d("Feature : ", "" + address.getFeatureName());
                        Log.d("More : ", "" + address.getLocality());
                    } else {
                        Log.d("Adddress", "Address Not Found");
                    }
                } else {
                    Log.d("Lat Lng", "Lat Lng Not Found");
                }

            }
        });
    }
        private LatLng getLatLngFromAddress(String address){

            Geocoder geocoder=new Geocoder(addressActivity.this);
            List<Address> addressList;

            try {
                addressList = geocoder.getFromLocationName(address, 1);
                if(addressList!=null){
                    Address singleaddress=addressList.get(0);
                    LatLng latLng=new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                    return latLng;
                }
                else{
                    return null;
                }
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        private Address getAddressFromLatLng(LatLng latLng){
            Geocoder geocoder=new Geocoder(addressActivity.this);
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
                if(addresses!=null){
                    Address address=addresses.get(0);
                    return address;
                }
                else{
                    return null;
                }
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }}
//    }


//        edit_text.setFocusable(false);
//        edit_text.setOnClickListener( v ->{
//            List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
//
//            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldList).build(addressActivity.this);
//
//            startActivityForResult(intent,100);
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 100 && resultCode == RESULT_OK){
//            Place place = Autocomplete.getPlaceFromIntent(data);
//            edit_text.setText(place.getAddress());
//            text_view1.setText(String.format("Locality Name :%s" ,place.getName()));
//            text_view2.setText(String.format("Locality address :%s  " ,place.getAddress()));
//        }else if(resultCode == AutocompleteActivity.RESULT_ERROR){
//            Status status = Autocomplete.getStatusFromIntent(data);
//            Toast.makeText(this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//};