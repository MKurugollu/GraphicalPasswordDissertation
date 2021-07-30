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

public class ActivityGP_2_SignUpFinalSuccess extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> selectedImages;
    //private ArrayList<Integer> selectedPass;

    private int gp1, gp2, gp3, gp4, gp5;

    DBHelper DB;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_2__sign_up_final_success);


        //selectedPass = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
            //selectedPass = extras.getIntegerArrayList("selectedPass");
        }
        gp1 = selectedImages.get(0);
        gp2 = selectedImages.get(1);
        gp3 = selectedImages.get(2);
        gp4 = selectedImages.get(3);
        gp5 = selectedImages.get(4);

        DB = new DBHelper(this);
        Boolean insert = DB.insertData2(username,gp1,gp2,gp3,gp4,gp5);
        if(insert){
            Toast.makeText(this, "registered successfully", Toast.LENGTH_LONG).show();
        }


        backButton = (Button) findViewById(R.id.buttonLogInGP2);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_2_SignUpFinalSuccess.this, ActivityGP_2_LogIn.class);
                startActivity(intent);
            }
        });
    }
}