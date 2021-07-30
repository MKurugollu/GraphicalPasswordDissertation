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

public class ActivityGP_1_LogIn extends AppCompatActivity {



    private Button signupButton, logInButton;
    private EditText gp1UsernameField;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__log_in);

        DB = new DBHelper(this);

        Boolean checkUsername = DB.checkusername("mustieTest");
        if(checkUsername == false) {
            Boolean insert = DB.insertData("mustieTest", 0, 1, 2, 3);
        }
        gp1UsernameField = (EditText) findViewById(R.id.gp1UsernameField);
        gp1UsernameField.addTextChangedListener(usernameTextWatcher);


        logInButton = (Button) findViewById(R.id.gp1LogInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = gp1UsernameField.getText().toString();
                Boolean checkuser = DB.checkusername(username);
                if(checkuser == true){
                    Intent intent = new Intent(ActivityGP_1_LogIn.this, ActivityGP_1_LogInFinal1.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupButton = (Button) findViewById(R.id.gp1RegisterButton);
        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_1_SignUp();
            }
        });
    }

    public void openActivityGP_1_SignUp() {
        Intent intent = new Intent(this, ActivityGP_1_SignUp.class);
        startActivity(intent);
    }

    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = gp1UsernameField.getText().toString().trim();
            logInButton.setEnabled(!usernameInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}