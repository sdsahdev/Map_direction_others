package com.example.array;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import kotlin.jvm.internal.Intrinsics;

public class MainActivity extends AppCompatActivity {

//
    static {
        System.loadLibrary("keys");
    }
    public native String getFacebookApiKey();
//    static {
//        System.loadLibrary("hello-jni");
//    }

    //    Button bt1;
//    Button bt2;
//    JSONArray arraytext = new JSONArray();
    URL url2;
    ImageView img;
    Bitmap bt7;
    Bitmap bitmap5;
//    GetFileFromBitmap getFileFromBitmap;

    @SuppressLint({"MissingInflatedId", "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);

        Log.d("check_nmds", "onCreate: "+getFacebookApiKey());
        {
            try {
                url2 = new URL("https://www.shorturl.at/img/shorturl-icon.png");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        new GetImageFromUrl(img).execute(String.valueOf(url2));

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @SuppressLint("LongLogTag")
                    public void run() {
                        Log.d("devi1 0","onCreate: "+bt7);
                        Log.d("devi1 2","onCreate: "+url2);
                        Log.d("devi1 3","onCreate: "+bitmap5);

                        //create a file to wrtite bitmap data
                        File f = new File(MainActivity.this.getCacheDir(),"123456.JPEG");
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //convert bitmat to byte by byte
                        Bitmap bitmap15 = bt7;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap15.compress(Bitmap.CompressFormat.JPEG,0,bos);
                        byte[] bitmapdata = bos.toByteArray();


                        //writte the bites in file
                        try {
                            FileOutputStream fos = new FileOutputStream(f);
                            fos.write(bitmapdata);
                            fos.flush();
                            fos.close();

                            Log.d("devi1 12", "run: "+f);
                        } catch (FileNotFoundException e) {
                            Log.d("devi1 FileNotFoundException ", "run: "+e);
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("devi1 FileNotFoundException2 ", "run: "+e);
                        }

//                        getFileFromBitmap.getFileFromBitmap(bt7,MainActivity.this);
                    }
                }, 3000);
}

public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public GetImageFromUrl(ImageView img) {
        Log.d("devi1 9", "GetImageFromUrl: ");
        this.imageView = img;
    }

    @Override
    public Bitmap doInBackground(String... url) {
        String stringUrl = url[0];
//            bitmap = null;
        InputStream inputStream;
        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap5 = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.d("getFileFromBitmap try", "doInBackground: " + e);
            e.printStackTrace();
        }
        return bitmap5;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        img.setImageBitmap(bitmap);
        Log.d("devi1 bit", "onPostExecute: " + bitmap);
        bt7 = bitmap;
    }
}

}