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

public class ActivityGP_1_SignUpFinalSuccess extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> selectedImages;
    private ArrayList<Integer> selectedPass;

    private int gp1, gp2, gp3, gp4;

    DBHelper DB;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__sign_up_final_success);


        selectedPass = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
            selectedPass = extras.getIntegerArrayList("selectedPass");
        }
        gp1 = selectedPass.get(0);
        gp2 = selectedPass.get(1);
        gp3 = selectedPass.get(2);
        gp4 = selectedPass.get(3);

        DB = new DBHelper(this);
        Boolean insert = DB.insertData(username,gp1,gp2,gp3,gp4);
        if(insert==true){
            Toast.makeText(this, "registered successfully", Toast.LENGTH_LONG).show();
        }


        backButton = (Button) findViewById(R.id.buttonLogIn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_1_SignUpFinalSuccess.this, ActivityGP_1_LogIn.class);
                startActivity(intent);
            }
        });
    }
}