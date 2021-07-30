package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityGP_3_LogInFinalFailure extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_3__log_in_final_failure);

        backButton = (Button) findViewById(R.id.backButton22);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_3_LogInFinalFailure.this, ActivityGP_3_LogIn.class);
                startActivity(intent);
            }
        });
    }
}