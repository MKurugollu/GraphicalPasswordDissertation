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

public class ActivityGP_3_LogIn extends AppCompatActivity {

    private Button gp3RegisterButton, gp3LogInButton;
    private EditText gp3UsernameField;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_3__log_in);

        DB = new DBHelper(this);
        Boolean checkUsername = DB.checkusername3("mustieTest");
        if(checkUsername == false) {
            Boolean insert = DB.insertData3("mustieTest", 0, 1, 2, 3, 4, 26, 27);
        }


        gp3UsernameField = (EditText) findViewById(R.id.gp3UsernameField);
        gp3UsernameField.addTextChangedListener(usernameTextWatcher);

        gp3LogInButton = (Button) findViewById(R.id.gp3LogInButton);
        gp3LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = gp3UsernameField.getText().toString();
                Boolean checkuser = DB.checkusername3(username);
                if(checkuser == true){
                    Intent intent = new Intent(ActivityGP_3_LogIn.this, ActivityGP_3_LogInFinal1.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getBaseContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gp3RegisterButton = (Button) findViewById(R.id.gp3RegisterButton);
        gp3RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGP_3_SignUp();
            }
        });
    }

    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = gp3UsernameField.getText().toString().trim();
            gp3LogInButton.setEnabled(!usernameInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void openActivityGP_3_SignUp() {
        Intent intent = new Intent(this, ActivityGP_3_SignUp.class);
        startActivity(intent);
    }
}