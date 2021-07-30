package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ActivityGP_4_SignUpPassword3 extends AppCompatActivity {

    private String username;
    private ArrayList<Integer> indicators, imgsList, pwIcons;

    private ImageView indicatorImage;
    private TypedArray imgs;
    private GridView gridViewGP4_signin3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__sign_up_password3);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            indicators = extras.getIntegerArrayList("indicators");
            imgsList = extras.getIntegerArrayList("imgsList");
            pwIcons = extras.getIntegerArrayList("pwIcons");
        }

        imgs = getResources().obtainTypedArray(R.array.images);


        indicatorImage = (ImageView) findViewById(R.id.indicator3);
        indicatorImage.setImageResource(imgs.getResourceId(indicators.get(2), 0));

        ArrayList<Integer> prePasswordImages = getRandomImgsArray();
        for(int i = 0; i< prePasswordImages.size(); i++){
            if(prePasswordImages.get(i) == indicators.get(2) || pwIcons.contains(prePasswordImages.get(i)) ){
                prePasswordImages.remove(i);
            }
        }
        gridViewGP4_signin3 = findViewById(R.id.gridViewGP4_signin3);
        CustomAdapter customAdapter = new CustomAdapter(prePasswordImages, this);
        gridViewGP4_signin3.setAdapter(customAdapter);
        //pwIcons = new ArrayList<Integer>();
        gridViewGP4_signin3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pwIcons.add(prePasswordImages.get(position));
                imgsList.remove(Integer.valueOf(imgsList.get(prePasswordImages.get(position))));
                openActivityGP_4_SignUpPassword4();
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
        while (prePasswordImages.size() < imgsList.size()){
            final int min = 0;
            final int max = imgsList.size()-1;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if (!prePasswordImages.contains(randomIndex)){
                prePasswordImages.add(randomIndex);
            }
        }
        return prePasswordImages;
    }

    public void openActivityGP_4_SignUpPassword4() {
        Intent intent = new Intent(this, ActivityGP_4_SignUpPassword4.class);
        Bundle extras = new Bundle();
        extras.putString("username", username);
        extras.putIntegerArrayList("imgsList", imgsList);
        extras.putIntegerArrayList("indicators", indicators);
        extras.putIntegerArrayList("pwIcons", pwIcons);
        intent.putExtras(extras);
        startActivity(intent);
    }
}