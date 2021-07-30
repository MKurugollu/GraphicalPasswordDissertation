package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityGP_4_SignUpFailure extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> selectedImages;
    private ArrayList<Integer> selectedPass, indicators, pwIcons;

    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__sign_up_failure);

        selectedPass = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
            selectedPass = extras.getIntegerArrayList("selectedPass");
            indicators = extras.getIntegerArrayList("indicators");
            pwIcons = extras.getIntegerArrayList("pwIcons");
        }

        Log.v("", ""+ indicators);



        backButton = (Button) findViewById(R.id.buttonretrygp4);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_4_SignUpFailure.this, ActivityGP_4_SignUpFinal1.class);
                Bundle extras = new Bundle();
                extras.putString("username", username);
                extras.putIntegerArrayList("selectedImages", selectedImages);
                extras.putIntegerArrayList("indicators", indicators);
                extras.putIntegerArrayList("pwIcons", pwIcons);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


    }
}