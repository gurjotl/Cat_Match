package com.example.jot.pussymatch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bio extends AppCompatActivity {
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        connection.disconnect();
        return new BitmapDrawable(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("nameb");
        String location = extras.getString("locationb");
        String pic = extras.getString("picb");
        String bio = extras.getString("biob");
        TextView namem = (TextView) findViewById(R.id.cat_name_bio);
        TextView locationm = (TextView) findViewById(R.id.location_bio);
        TextView biom = (TextView) findViewById(R.id.bio);
        namem.setText(name);
        locationm.setText(location);
        biom.setText(bio);
        Drawable d = getResources().getDrawable(R.drawable.simba);;
        try{
            d = drawableFromUrl(pic);
        }catch(IOException e){
            //wut
        }
        ImageView picb = (ImageView) findViewById(R.id.cat_pic_bio);
        picb.setBackground(d);
    }
}
