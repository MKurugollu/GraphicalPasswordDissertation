package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityGP_1_SignUpFinalFailure extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> selectedImages;
    private ArrayList<Integer> selectedPass;

    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__sign_up_final_failure);

        selectedPass = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
            selectedPass = extras.getIntegerArrayList("selectedPass");
        }

        backButton = (Button) findViewById(R.id.buttonretry);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_1_SignUpFinalFailure.this, ActivityGP_1_SignUpFinal1.class);
                Bundle extras = new Bundle();
                extras.putString("username", username);
                extras.putIntegerArrayList("selectedImages", selectedImages);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


    }
}