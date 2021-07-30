package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityGP_1_LogInSuccess extends AppCompatActivity {

    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__log_in_success);

        menuButton = (Button) findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGP_1_LogInSuccess.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}