package com.example.array;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwoActivity extends AppCompatActivity {
    Button bt2, bt3;
    JSONArray jsonArray2 = new JSONArray();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);

        bt3.setOnClickListener(v -> {
            Log.d("devi1", "onCreate: 2 ");
            Bundle b = getIntent().getExtras();
            String arraytext = b.getString("Array");

            try {

                // ADD
                JSONObject object = new JSONObject();

                JSONArray jsonArray = new JSONArray(arraytext);
                object.put("Address2", "Address2");
                jsonArray.put(object);

                Log.d("devi1", "onCreate: 2 " + jsonArray);

                jsonArray2 = jsonArray;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


        bt2.setOnClickListener(v -> {
            Intent intent = new Intent(TwoActivity.this, TreeActivity.class);

            intent.putExtra("Array", String.valueOf(jsonArray2));
            startActivity(intent);
        });

    }
}