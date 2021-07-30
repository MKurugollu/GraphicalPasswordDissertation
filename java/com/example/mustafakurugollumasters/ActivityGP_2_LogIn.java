package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityGP_2_LogIn extends AppCompatActivity {

    private Button gp2RegisterButton, gp2LogInButton;
    private EditText gp2UsernameField;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_2__log_in);

        DB = new DBHelper(this);
        Boolean checkUsername = DB.checkusername2("mustieTest");
        if(checkUsername == false) {
            Boolean insert = DB.insertData2("mustieTest", 0, 1, 2, 3, 4);
        }

        gp2UsernameField = (EditText) findViewById(R.id.gp2UsernameField);
        gp2UsernameField.addTextChangedListener(usernameTextWatcher);


        gp2LogInButton = (Button) findViewById(R.id.gp2LogInButton);
        gp2LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = gp2UsernameField.getText().toString();
                Boolean checkuser = DB.checkusername(username);
                if(checkuser == true){
                    Intent intent = new Intent(ActivityGP_2_LogIn.this, ActivityGP_2_LogInFinal1.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gp2RegisterButton = (Button) findViewById(R.id.gp2RegisterButton);
        gp2RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_2_SignUp();
            }
        });
    }


    public void openActivityGP_2_SignUp() {
        Intent intent = new Intent(this, ActivityGP_2_SignUp.class);
        startActivity(intent);
    }

    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = gp2UsernameField.getText().toString().trim();
            gp2LogInButton.setEnabled(!usernameInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}