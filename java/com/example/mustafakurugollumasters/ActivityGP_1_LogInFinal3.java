package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ActivityGP_1_LogInFinal3 extends AppCompatActivity {

    private String username;
    private TypedArray imgs;
    private ArrayList<Integer> selectedPass;


    private GridView gridViewGP1_login3;
    private EditText coordsEnter;
    private int gp1coords3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_1__log_in_final3);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedPass = extras.getIntegerArrayList("selectedPass");
        }

        imgs = getResources().obtainTypedArray(R.array.images);
        ArrayList<Integer> prePasswordImages = getRandomImgsArray();

        gridViewGP1_login3 = findViewById(R.id.gridViewGP1_login3);
        CustomAdapter customAdapter = new CustomAdapter(prePasswordImages, this);
        gridViewGP1_login3.setAdapter(customAdapter);

        gridViewGP1_login3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPass.add(prePasswordImages.get(position));
                openActivityGP_1_LogIn4();
            }
        });

        coordsEnter = (EditText) findViewById(R.id.gp1coords3);
        coordsEnter.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    gp1coords3 = Integer.parseInt(coordsEnter.getText().toString());
                    int digits = (int)(Math.log10(gp1coords3)+1);
                    if (digits == 2){
                        int col = Character.getNumericValue(coordsEnter.getText().toString().charAt(0));
                        int row = Character.getNumericValue(coordsEnter.getText().toString().charAt(1));
                        int coordToIndex = ((row - 1)*4 + col)-1;
                        selectedPass.add(prePasswordImages.get(coordToIndex));

                        openActivityGP_1_LogIn4();
                    }
                    else{
                        selectedPass.add(-1);
                        openActivityGP_1_LogIn4();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public class CustomAdapter extends BaseAdapter {
        private ArrayList<Integer> imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(ArrayList<Integer> imagesPhoto, Context context) {
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_items_password, viewGroup, false);

            }

            ImageView imageView = view.findViewById(R.id.imageViewPassword);

            int imgID = imagesPhoto.get(i);
            imageView.setImageResource(imgs.getResourceId(imgID, 0));


            return view;
        }
    }

    public ArrayList<Integer> getRandomImgsArray(){
        ArrayList<Integer> prePasswordImages = new ArrayList<Integer>();
        while (prePasswordImages.size() < 28){
            final int min = 0;
            final int max = 27;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if (!prePasswordImages.contains(randomIndex)){
                prePasswordImages.add(randomIndex);
            }
        }
        return prePasswordImages;
    }

    public void openActivityGP_1_LogIn4() {
        Intent intent = new Intent(this, ActivityGP_1_LogInFinal4.class);
        Bundle extras = new Bundle();
        extras.putString("username", username);
        //extras.putIntegerArrayList("selectedImages", selectedImages);
        extras.putIntegerArrayList("selectedPass", selectedPass);
        intent.putExtras(extras);
        startActivity(intent);
    }
}