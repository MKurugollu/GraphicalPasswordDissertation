package com.example.mustafakurugollumasters;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ActivityGP_3_SignUpFinal1 extends AppCompatActivity {

    private String username;
    private TypedArray imgs;
    private List<Integer> imgs2;
    //private List<Integer> gp3Items;
    private List<Integer> imgsList;
    private List<Integer> gridImgs2;
    private GridView gridViewGP3_login;
    //private RelativeLayout rl_gp3;
    private List<Integer> randGridImgs;
    private Rect rect;
    private Polygon.Point[] polygons;
    private List<Integer> touchedImages;
    private ArrayList<Integer> selectedImages;


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_3__sign_up_final1);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
            selectedImages = extras.getIntegerArrayList("selectedImages");
        }

        Log.v("here3", username);
        imgs = getResources().obtainTypedArray(R.array.images);

        imgsList = IntStream.rangeClosed(0, imgs.length()-1).boxed().collect(Collectors.toList());

        for(int i=0; i<2; i++){
            final int min = 0;
            final int max = imgs.length()-1;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if(!selectedImages.contains(imgsList.get(randomIndex)) ){
                selectedImages.add(imgsList.get(randomIndex));
            }
        }

        for(int i: selectedImages) {
            imgsList.remove(Integer.valueOf(i));
        }


        gridImgs2 = Stream.concat(selectedImages.stream(), imgsList.subList(0, 17).stream()).collect(Collectors.toList());
        randGridImgs = getRandomImgsArray();

        gridViewGP3_login = (GridView) findViewById(R.id.gridViewGP3_SignUpfinal);

        CustomAdapter customAdapter = new CustomAdapter(randGridImgs, this);
        gridViewGP3_login.setAdapter(customAdapter);


        touchedImages = new ArrayList<Integer>();
        gridViewGP3_login.setOnTouchListener(new View.OnTouchListener(){
            int tempID = -1;
            int addedToArray = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Rect myRec = new Rect();
                for(int i=0; i < gridViewGP3_login.getCount(); i++) {
                    int[] location = new int[2];
                    gridViewGP3_login.getChildAt(i).getLocationOnScreen(location);
                    myRec.set(location[0]+6, location[1]+11, location[0] + gridViewGP3_login.getChildAt(i).getWidth() - 26, location[1] + gridViewGP3_login.getChildAt(i).getHeight() - 32);
                    if (myRec.contains((int) event.getRawX(), (int) event.getRawY()) && tempID !=randGridImgs.get(i) && selectedImages.contains(randGridImgs.get(i))) {
                        //Log.v("h", "" + randGridImgs.get(i));
                        if(touchedImages.size() == 0){
                            Log.v("image", "" + randGridImgs.get(i));
                            touchedImages.add(randGridImgs.get(i));
                            tempID = randGridImgs.get(i);
                        }
                        else{
                            if((!touchedImages.contains(selectedImages.get(addedToArray)) || !touchedImages.contains(selectedImages.get(selectedImages.size()-1))) && Integer.toString(touchedImages.get(addedToArray)).equals(Integer.toString(tempID))){
                                if(selectedImages.get(addedToArray) == randGridImgs.get(i) || selectedImages.get(selectedImages.size()-1) == randGridImgs.get(i)){
                                    if(randGridImgs.get(i) == selectedImages.get(selectedImages.size()-1) && touchedImages.size() == 6 ){
                                        Log.v("image", "" + randGridImgs.get(i));
                                        touchedImages.add(randGridImgs.get(i));
                                        addedToArray++;
                                        tempID = randGridImgs.get(i);
                                    }
                                    else if (touchedImages.size() < 6 && randGridImgs.get(i) != selectedImages.get(selectedImages.size()-1)){
                                        Log.v("image", "" + randGridImgs.get(i));
                                        touchedImages.add(randGridImgs.get(i));
                                        addedToArray++;
                                        tempID = randGridImgs.get(i);
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Intent intent;
                    Bundle extras = new Bundle();
                    extras.putString("username", username);
                    extras.putIntegerArrayList("selectedImages", selectedImages);
                    if(isValidPW(touchedImages.get(0), touchedImages.get(touchedImages.size()-1), touchedImages))
                        intent = new Intent(ActivityGP_3_SignUpFinal1.this, ActivityGP_3_SignUpFinalSuccess.class);
                    else
                        intent = new Intent(ActivityGP_3_SignUpFinal1.this, ActivityGP_3_SignUpFinalFailure.class);
                    intent.putExtras(extras);
                    startActivity(intent);
                    return false;
                }
                return true;
            }
        });

    }
    public boolean isValidPW(int start, int finish, List<Integer> touchedImages){
        boolean isCorrect = true;
        if (touchedImages.size() > 2) {
            if (touchedImages.size() != 7)
                isCorrect = false;
            if (start != selectedImages.get(selectedImages.size() - 2) || finish != selectedImages.get(selectedImages.size() - 1)) {
                isCorrect = false;
            }
            touchedImages.remove(0);
            touchedImages.remove(touchedImages.size() - 1);
            for (int i = 0; i < touchedImages.size(); i++) {
                if (!Integer.toString(touchedImages.get(i)).equals(Integer.toString(selectedImages.get(i)))) {
                    Log.v("touched", "" + touchedImages.get(i));
                    Log.v("Gp3", "" + selectedImages.get(i));
                    isCorrect = false;
                }
            }
        }
        else{
            isCorrect = false;
        }
        return isCorrect;
    }

    public class CustomAdapter extends BaseAdapter {
        private List<Integer> imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(List<Integer> imagesPhoto, Context context) {
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
                view = layoutInflater.inflate(R.layout.row_items_password_gp3, viewGroup, false);
            }

            ImageView imageView = view.findViewById(R.id.imageViewPassword_gp3);

            imageView.setColorFilter(applyLightness(50));

            int imgID = imagesPhoto.get(i);
            imageView.setImageResource(imgs.getResourceId(imgID, 0));

            if(imgID == selectedImages.get(selectedImages.size()-1))
                imageView.setBackgroundResource(R.drawable.image_border_finish);

            if(imgID == selectedImages.get(selectedImages.size()-2))
                imageView.setBackgroundResource(R.drawable.image_border_start);

            return view;
        }
    }

    public List<Integer> getRandomImgsArray(){
        List<Integer> prePasswordImages2 = new ArrayList<Integer>();
        while (prePasswordImages2.size() < 24){
            final int min = 0;
            final int max = gridImgs2.size()-1;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if (!prePasswordImages2.contains(gridImgs2.get(randomIndex))){
                prePasswordImages2.add(gridImgs2.get(randomIndex));
            }
        }
        return prePasswordImages2;
    }


    public static PorterDuffColorFilter applyLightness(int progress)
    {
        if (progress > 0)
        {
            int value = (int) progress * 255 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 255, 255, 255), PorterDuff.Mode.SRC_OVER);
        }
        else
        {
            int value = (int) (progress * -1) * 255 / 100;
            return new PorterDuffColorFilter(Color.argb(value, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
        }
    }

}