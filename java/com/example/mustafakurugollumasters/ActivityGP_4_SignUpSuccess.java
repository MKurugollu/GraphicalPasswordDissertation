package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityGP_4_SignUpSuccess extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> selectedImages;
    private ArrayList<Integer> selectedPass, pwIcons, indicators;

    private int gp1, gp2, gp3, gp4, gp5, ind1, ind2, ind3, ind4, ind5;

    DBHelper DB;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__sign_up_success);


        selectedPass = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
            pwIcons = extras.getIntegerArrayList("pwIcons");
            indicators = extras.getIntegerArrayList("indicators");
        }
        gp1 = pwIcons.get(0);
        gp2 = pwIcons.get(1);
        gp3 = pwIcons.get(2);
        gp4 = pwIcons.get(3);
        gp5 = pwIcons.get(4);

        ind1 = indicators.get(0);
        ind2 = indicators.get(1);
        ind3 = indicators.get(2);
        ind4 = indicators.get(3);
        ind5 = indicators.get(4);

        DB = new DBHelper(this);
        Boolean insert = DB.insertData4(username,gp1,gp2,gp3,gp4,gp5,ind1,ind2,ind3,ind4,ind5);
        if(insert==true){
            Toast.makeText(this, "registered successfully", Toast.LENGTH_LONG).show();
        }


        backButton = (Button) findViewById(R.id.buttonLogIn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_4_SignUpSuccess.this, ActivityGP_4_LogIn.class);
                startActivity(intent);
            }
        });
    }
}