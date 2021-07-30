package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ActivityGP_4_LogIn extends AppCompatActivity {

    private Button gp4RegisterButton, gp4LogInButton;
    private EditText gp4UsernameField;

    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__log_in);

        DB = new DBHelper(this);
        Boolean checkUsername = DB.checkusername4("mustieTest");
        if(checkUsername == false) {
            Boolean insert = DB.insertData4("mustieTest", 0, 1, 2, 3, 4, 27, 26, 25, 24, 23);
        }


        gp4UsernameField = (EditText) findViewById(R.id.gp4UsernameField);
        gp4UsernameField.addTextChangedListener(usernameTextWatcher);

        gp4LogInButton = (Button) findViewById(R.id.gp4LogInButton);
        gp4LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = gp4UsernameField.getText().toString();
                Boolean checkuser = DB.checkusername4(username);
                if(checkuser == true){
                    ArrayList<Integer> gp4Items = DB.getGP4Indicators(username);
                    ArrayList<Integer> gp4Icons = DB.getGP4PIcons(username);
                    ArrayList<Integer> gp4Indicators = new ArrayList<Integer>();
                    ArrayList<Integer> gp4PWs = new ArrayList<Integer>();

                    while(gp4Indicators.size() < 3){
                        final int min = 0;
                        final int max = 4;
                        final int randomindex = new Random().nextInt((max - min) + 1) + min;
                        if(!gp4Indicators.contains(gp4Items.get(randomindex))){
                            gp4Indicators.add(gp4Items.get(randomindex));
                            gp4PWs.add(gp4Icons.get(randomindex));
                        }
                    }

                    Intent intent = new Intent(ActivityGP_4_LogIn.this, ActivityGP_4_LogInFinal1.class);
                    intent.putExtra("username", username);
                    intent.putExtra("gp4Indicators", gp4Indicators);
                    intent.putExtra("gp4Icons", gp4PWs);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gp4RegisterButton = (Button) findViewById(R.id.gp4RegisterButton);
        gp4RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_4_SignUp();
            }
        });

    }

    public void openActivityGP_4_SignUp() {
        Intent intent = new Intent(this, ActivityGP_4_SignUp.class);
        startActivity(intent);
    }

    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = gp4UsernameField.getText().toString().trim();
            gp4LogInButton.setEnabled(!usernameInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}