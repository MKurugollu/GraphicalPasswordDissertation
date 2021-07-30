package com.example.mustafakurugollumasters;

import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button menuButton1;
    private Button menuButton2;
    private Button menuButton3;
    private Button menuButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuButton1 = (Button) findViewById(R.id.menuButton1);
        menuButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_1_LogIn();
            }
        });

        menuButton2 = (Button) findViewById(R.id.menuButton2);
        menuButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_2_LogIn();
            }
        });

        menuButton3 = (Button) findViewById(R.id.menuButton3);
        menuButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_3_LogIn();
            }
        });

        menuButton4 = (Button) findViewById(R.id.menuButton4);
        menuButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_4_LogIn();
            }
        });

/*        TypedArray imgs = getResources().obtainTypedArray(R.array.images);
        Log.v("index count", Integer.toString(imgs.length()));

        ImageView helloworldimg = (ImageView) findViewById(R.id.imageView);
        final int min = 0;
        final int max = imgs.length()+1;
        final int randomindex = new Random().nextInt((max - min) + 1) + min;
        helloworldimg.setImageResource(imgs.getResourceId(randomindex, 0));*/
/*
        ImageView testimage = (ImageView) findViewById(R.id.imageView2);

        Resources r = getResources();
        Drawable[] layers = new Drawable[2];

        layers[0] = r.getDrawable(R.drawable.apples);
        layers[1] = r.getDrawable(R.drawable.cruise);
        LayerDrawable layers2 = new LayerDrawable(layers);
        testimage.setImageDrawable(layers2);*/


    }
    public void openActivityGP_1_LogIn() {
        Intent intent = new Intent(this, ActivityGP_1_LogIn.class);
        startActivity(intent);
    }
    public void openActivityGP_2_LogIn() {
        Intent intent = new Intent(this, ActivityGP_2_LogIn.class);
        startActivity(intent);
    }
    public void openActivityGP_3_LogIn() {
        Intent intent = new Intent(this, ActivityGP_3_LogIn.class);
        startActivity(intent);
    }
    public void openActivityGP_4_LogIn() {
        Intent intent = new Intent(this, ActivityGP_4_LogIn.class);
        startActivity(intent);
    }
}