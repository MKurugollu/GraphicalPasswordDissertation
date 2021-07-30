package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;

public class ActivityGP_1_SignUp extends AppCompatActivity {

    private EditText myUsername;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__sign_up);

        nextButton = (Button)findViewById(R.id.gp1SignUpNext);
        myUsername = (EditText)findViewById(R.id.gp1UsernameField_signup);

        myUsername.addTextChangedListener(usernameTextWatcher);

        nextButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Log.v("EditText", myUsername.getText().toString());
                        openActivityGP_1_SignUpPassword();
                    }
                }
        );
    }

    //enable button if there is text in username field
    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = myUsername.getText().toString().trim();

            nextButton.setEnabled(!usernameInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void openActivityGP_1_SignUpPassword() {
        Intent intent = new Intent(this, ActivityGP_1_SignUpPassword.class);
        intent.putExtra("username", myUsername.getText().toString());
        startActivity(intent);
    }
}