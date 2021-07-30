package com.example.mustafakurugollumasters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActivityGP_4_LogInFinal2 extends AppCompatActivity {

    private String username;
    private List<Integer> imgsList, gridImgs;
    private ArrayList<Integer> gp4Icons, gp4Indicators, selectedItems;
    private int indicator, icon, gp4coords;
    private TypedArray imgs;
    private GridView gridViewGP4_login2;
    private EditText coordsEnter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_4__log_in_final2);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            gp4Icons = extras.getIntegerArrayList("gp4Icons");
            gp4Indicators = extras.getIntegerArrayList("gp4Indicators");
            selectedItems = extras.getIntegerArrayList("selectedItems");
        }

        indicator = gp4Indicators.get(1);
        icon = gp4Icons.get(1);

        imgs = getResources().obtainTypedArray(R.array.images);
        imgsList = IntStream.rangeClosed(0, imgs.length()-1).boxed().collect(Collectors.toList());
        imgsList.remove(Integer.valueOf(indicator));
        imgsList.remove(Integer.valueOf(icon));

        ArrayList<Integer> prePasswordImages = getRandomImgsArray();

        final int min = 0;
        final int max = 23;
        final int randomIndex = new Random().nextInt((max - min) + 1) + min;
        prePasswordImages.add(randomIndex, icon);
        gridViewGP4_login2 = findViewById(R.id.gridViewGP4_login2);
        CustomAdapter customAdapter = new CustomAdapter(prePasswordImages, this);
        gridViewGP4_login2.setAdapter(customAdapter);

        gridViewGP4_login2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(prePasswordImages.get(position) == icon)
                    selectedItems.add(1);
                else
                    selectedItems.add(0);

                openActivityGP_4_LogInFinal2();
            }
        });


        coordsEnter = (EditText) findViewById(R.id.gp4coords2);
        coordsEnter.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    gp4coords = Integer.parseInt(coordsEnter.getText().toString());
                    int digits = (int)(Math.log10(gp4coords)+1);
                    if (digits == 2){
                        int col = Character.getNumericValue(coordsEnter.getText().toString().charAt(0));
                        int row = Character.getNumericValue(coordsEnter.getText().toString().charAt(1));
                        int coordToIndex = ((row - 1)*4 + col)-1;

                        if(prePasswordImages.get(coordToIndex) == icon)
                            selectedItems.add(1);
                        else
                            selectedItems.add(0);


                        openActivityGP_4_LogInFinal2();
                    }
                    else{
                        selectedItems.add(-1);
                        openActivityGP_4_LogInFinal2();
                    }
                    return true;
                }
                return false;
            }
        });


    }

    public void openActivityGP_4_LogInFinal2() {
        Intent intent = new Intent(this, ActivityGP_4_LogInFinal3.class);
        Bundle extras = new Bundle();
        extras.putString("username", username);
        extras.putIntegerArrayList("selectedItems", selectedItems);
        extras.putIntegerArrayList("gp4Icons", gp4Icons);
        extras.putIntegerArrayList("gp4Indicators", gp4Indicators);
        intent.putExtras(extras);
        startActivity(intent);
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
                view = layoutInflater.inflate(R.layout.row_items_password_gp4, viewGroup, false);

            }

            ImageView imageView = view.findViewById(R.id.imageViewPasswordgp4);
            int imgID = imagesPhoto.get(i);

            Resources r = getResources();
            Drawable[] layers = new Drawable[2];

            layers[0] = r.getDrawable(imgs.getResourceId(imgID,0));
            layers[1] = r.getDrawable(imgs.getResourceId(indicator, 0));
            layers[1].setAlpha(180);
            LayerDrawable layers2 = new LayerDrawable(layers);
            imageView.setImageDrawable(layers2);/*

            imageView.setImageResource(imgs.getResourceId(imgID, 0));*/


            return view;
        }
    }


    public ArrayList<Integer> getRandomImgsArray(){
        ArrayList<Integer> prePasswordImages = new ArrayList<Integer>();
        while (prePasswordImages.size() < 23){
            final int min = 0;
            final int max = imgsList.size()-1;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if (!prePasswordImages.contains(imgsList.get(randomIndex))){
                prePasswordImages.add(imgsList.get(randomIndex));
            }
        }
        return prePasswordImages;
    }
}