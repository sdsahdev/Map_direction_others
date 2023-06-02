package com.example.array;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.Collections;
import java.util.List;

public class Firebase_checkinng extends AppCompatActivity {
TextView text1;
TextView bt1;
EditText edit1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_checkinng);

        text1 = findViewById(R.id.text1);
        edit1 = findViewById(R.id.edit1);
        bt1 = findViewById(R.id.btm1);
        bt1.setOnClickListener(v->{
            FirebaseApp.initializeApp(Firebase_checkinng.this);
            FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
                @Override
                public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                    Log.d("deviooo", "onSuccess: ");
                    Uri deeplink = null;
                    if(pendingDynamicLinkData != null){
                        deeplink = pendingDynamicLinkData.getLink();
                        Log.d("deviooo", "onSuccess: "+deeplink);
                        text1.setText(deeplink.toString());
                    }else{
                        Log.d("deviooo", "onSuccess: ");
                    }
                    if (deeplink != null){
                        Log.d("deviooo", "onSuccess: "+deeplink.toString());
                        String currepage = deeplink.getQueryParameter("curpage");
//                    int curp = Integer.parseInt(currepage);
                    }
                    else {
                        Log.d("deviooo", "onSuccess: 2");
                    }
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("deviooo", "onFailure: "+e);
                }
            });
        });
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                Log.d("devi78", "onTextChanged: "+edit1.getLineCount());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Uri uri124 = getIntent().getData();
        if(uri124 != null){
            List<String> params = uri124.getPathSegments();
            String params2 = uri124.getQuery();
            List<String> params3 = Collections.singletonList(uri124.getUserInfo());
            List<String> params4 = Collections.singletonList(uri124.getPath());
            Log.d("devi89", "onCreate: "+params2);
            String id145 = params.get(params.size() -1 );
            Toast.makeText(this, "id = "+id145, Toast.LENGTH_SHORT).show();
        }
    }
}