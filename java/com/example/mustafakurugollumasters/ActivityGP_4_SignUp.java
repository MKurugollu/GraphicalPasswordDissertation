package com.example.mustafakurugollumasters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActivityGP_4_SignUp extends AppCompatActivity {

    private EditText myUsername;
    private Button nextButton;
    private ArrayList<Integer> indicators;
    private TypedArray imgs;
    private ArrayList<Integer> imgsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__sign_up);

        nextButton = (Button)findViewById(R.id.gp4SignUpNext);
        myUsername = (EditText)findViewById(R.id.gp4UsernameField_signup);

        myUsername.addTextChangedListener(usernameTextWatcher);

        nextButton.setOnClickListener(
                new View.OnClickListener(){
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(View view){
                        indicators = new ArrayList<Integer>();
                        imgs = getResources().obtainTypedArray(R.array.images);
                        imgsList = (ArrayList<Integer>) IntStream.rangeClosed(0, imgs.length()-1).boxed().collect(Collectors.toList());


                        while (indicators.size() < 5){
                            final int min = 0;
                            final int max = imgsList.size()-1;
                            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
                            if (!indicators.contains(imgsList.get(randomIndex))){
                                indicators.add(imgsList.get(randomIndex));
                                imgsList.remove(Integer.valueOf(imgsList.get(randomIndex)));
                            }
                        }

                        //Log.v("indicators", "" + indicators);
                        //Log.v("imgsList length", "" + imgsList.size());


                        openActivityGP_4_SignUpPassword();
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

    public void openActivityGP_4_SignUpPassword() {
        Intent intent = new Intent(this, ActivityGP_4_SignUpPassword1.class);
        intent.putExtra("username", myUsername.getText().toString());
        intent.putExtra("indicators", indicators);
        intent.putExtra("imgs", imgsList);
        startActivity(intent);
    }
}