package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityGP_4_LogInSuccess extends AppCompatActivity {

    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__log_in_success);

        menuButton = (Button) findViewById(R.id.menuButtongp4);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_4_LogInSuccess.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}