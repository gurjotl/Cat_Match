package com.example.jot.pussymatch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import com.kumulos.android.jsonclient.Kumulos;
import com.kumulos.android.jsonclient.ResponseHandler;

import org.w3c.dom.Text;
import com.example.jot.pussymatch.swipe;

public class MainActivity extends AppCompatActivity {
    public String name = "";
    public String age = "";
    public String location = "";
    public String bio = "";
    public String pic = "";
    public String namecopy = "";
    public String agecopy = "";
    public String locationcopy = "";
    public String biocopy = "";
    public String piccopy = "";
    public boolean first = true;

    public int count = 1;
    public int max = 14;

    MediaPlayer hissmp;
    MediaPlayer meowmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Kumulos.initWithAPIKeyAndSecretKey("b48c50mmp18f4q22f7d710kd0m0m9qsc", "bms1zr72", this);
        meowmp = MediaPlayer.create(this, R.raw.meoww);
        hissmp = MediaPlayer.create(this, R.raw.hiss);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/avenirnext-regular.ttf");
        TextView blogo = (TextView) findViewById(R.id.pussymatch);
        blogo.setTypeface(font2);

        String param = "" + count;
        HashMap<String, String> params = new HashMap();
        params.put("userID", param);
        Kumulos.call("getUserInfo", params, new ResponseHandler() {
            @Override
            public void didCompleteWithResult(Object result) {
                result = (ArrayList<Object>) result;
                LinkedHashMap<String, Object> entry1 = (LinkedHashMap<String, Object>) ((ArrayList<Object>) result).get(0);
                age = entry1.get("age").toString();
                location = entry1.get("location").toString();
                name = entry1.get("name").toString() + ", " + age + " »";
                pic = entry1.get("picture").toString();
                bio = entry1.get("bio").toString();
            }

        });
        ImageView pict = (ImageView) findViewById(R.id.cat_pic);

        pict.setOnTouchListener(new swipe(this) {

            public void onSwipeRight() {
                meowmp.start();
                Intent intent = new Intent(MainActivity.this, heart.class);
                startActivity(intent);
                Drawable d = getResources().getDrawable(R.drawable.simba);


                if (count == max){
                    count = 0;
                }
                count++;

                String param = "" + count;

                HashMap<String, String> params = new HashMap();
                params.put("userID", param);
                namecopy = name;
                agecopy = age;
                locationcopy = location;
                biocopy = bio;
                piccopy = pic;
                Kumulos.call("getUserInfo", params, new ResponseHandler() {
                    @Override
                    public void didCompleteWithResult(Object result) {
                        result = (ArrayList<Object>)result;
                        LinkedHashMap<String, Object> entry1 = (LinkedHashMap<String, Object>) ((ArrayList<Object>) result).get(0);
                        age = entry1.get("age").toString();
                        location = entry1.get("location").toString();
                        name = entry1.get("name").toString() + ", " + age + " »";
                        pic = entry1.get("picture").toString();
                        bio = entry1.get("bio").toString();
                    }
                });

                try{
                    d = drawableFromUrl(pic);
                }catch(IOException e){
                    //wut
                }
                TextView namem = (TextView) findViewById(R.id.cat_name);
                TextView locationm = (TextView) findViewById(R.id.location);
                namem.setText(name);
                locationm.setText(location);
                ImageView pic = (ImageView) findViewById(R.id.cat_pic);
                pic.setBackground(d);
            }

            public void onSwipeLeft() {
                hissmp.start();
                Intent intent = new Intent(MainActivity.this, brokenheart.class);
                startActivity(intent);
                Drawable d = getResources().getDrawable(R.drawable.simba);

                if (count == max){
                    count = 0;
                }
                count++;
                String param = "" + count;

                HashMap<String, String> params = new HashMap();
                params.put("userID", param);

                namecopy = name;
                agecopy = age;
                locationcopy = location;
                biocopy = bio;
                piccopy = pic;
                Kumulos.call("getUserInfo", params, new ResponseHandler() {
                    @Override
                    public void didCompleteWithResult(Object result) {
                        result = (ArrayList<Object>)result;
                        LinkedHashMap<String, Object> entry1 = (LinkedHashMap<String, Object>) ((ArrayList<Object>) result).get(0);
                        age = entry1.get("age").toString();
                        location = entry1.get("location").toString();
                        name = entry1.get("name").toString() + ", " + age + " »";
                        pic = entry1.get("picture").toString();
                        bio = entry1.get("bio").toString();
                    }
                });

                try{
                    d = drawableFromUrl(pic);
                }catch(IOException e){
                    //wut
                }
                TextView namem = (TextView) findViewById(R.id.cat_name);
                TextView locationm = (TextView) findViewById(R.id.location);
                namem.setText(name);
                locationm.setText(location);
                ImageView pic = (ImageView) findViewById(R.id.cat_pic);
                pic.setBackground(d);
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        connection.disconnect();
        return new BitmapDrawable(x);
    }

    public void meow(View view){
        meowmp.start();
        Intent intent = new Intent(MainActivity.this, heart.class);
        startActivity(intent);
        Drawable d = getResources().getDrawable(R.drawable.simba);

        if (count == max){
            count = 0;
        }
        count++;
        String param = "" + count;

        HashMap<String, String> params = new HashMap();
        params.put("userID", param);

        namecopy = name;
        agecopy = age;
        locationcopy = location;
        biocopy = bio;
        piccopy = pic;
        Kumulos.call("getUserInfo", params, new ResponseHandler() {
            @Override
            public void didCompleteWithResult(Object result) {
                result = (ArrayList<Object>)result;
                LinkedHashMap<String, Object> entry1 = (LinkedHashMap<String, Object>) ((ArrayList<Object>) result).get(0);
                age = entry1.get("age").toString();
                location = entry1.get("location").toString();
                name = entry1.get("name").toString() + ", " + age + " »";
                pic = entry1.get("picture").toString();
                bio = entry1.get("bio").toString();
            }
        });

        try{
            d = drawableFromUrl(pic);
        }catch(IOException e){
            //wut
        }
        TextView namem = (TextView) findViewById(R.id.cat_name);
        TextView locationm = (TextView) findViewById(R.id.location);
        namem.setText(name);
        locationm.setText(location);
        ImageView pic = (ImageView) findViewById(R.id.cat_pic);
        pic.setBackground(d);
    }

    public void hiss(View view){
        hissmp.start();
        Intent intent = new Intent(MainActivity.this, brokenheart.class);
        startActivity(intent);
        Drawable d = getResources().getDrawable(R.drawable.simba);


        if (count == max){
            count = 0;
        }
        count++;
        String param = "" + count;

        HashMap<String, String> params = new HashMap();
        params.put("userID", param);

        namecopy = name;
        agecopy = age;
        locationcopy = location;
        biocopy = bio;
        piccopy = pic;
        Kumulos.call("getUserInfo", params, new ResponseHandler() {
            @Override
            public void didCompleteWithResult(Object result) {
                result = (ArrayList<Object>)result;
                LinkedHashMap<String, Object> entry1 = (LinkedHashMap<String, Object>) ((ArrayList<Object>) result).get(0);
                age = entry1.get("age").toString();
                location = entry1.get("location").toString();
                name = entry1.get("name").toString() + ", " + age + " »";
                pic = entry1.get("picture").toString();
                bio = entry1.get("bio").toString();
            }
        });

        try{
            d = drawableFromUrl(pic);
        }catch(IOException e){
            //wut
        }
        TextView namem = (TextView) findViewById(R.id.cat_name);
        TextView locationm = (TextView) findViewById(R.id.location);
        namem.setText(name);
        locationm.setText(location);
        ImageView pic = (ImageView) findViewById(R.id.cat_pic);
        pic.setBackground(d);
    }

    public void bioo(View v){
        Intent intent = new Intent(getBaseContext(), bio.class);
        intent.putExtra("nameb", namecopy);
        intent.putExtra("locationb", locationcopy);
        intent.putExtra("picb", piccopy);
        intent.putExtra("biob", biocopy);
        startActivity(intent);
    }
}
