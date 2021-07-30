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

public class ActivityGP_3_LogInFinal1 extends AppCompatActivity {

    private String username;
    private TypedArray imgs;
    private List<Integer> imgs2;
    private List<Integer> gp3Items;
    private List<Integer> imgsList;
    private List<Integer> gridImgs;
    private GridView gridViewGP3_login;
    private RelativeLayout rl_gp3;
    private List<Integer> randGridImgs;
    private Rect rect;
    private Polygon.Point[] polygons;
    private List<Integer> touchedImages;

    DBHelper myDb;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_3__log_in_final1);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
        }
        imgs = getResources().obtainTypedArray(R.array.images);
        myDb = new DBHelper(this);
        gp3Items = myDb.getGP3Items(username);

        imgsList = IntStream.rangeClosed(0, imgs.length()-1).boxed().collect(Collectors.toList());
        for(int i: gp3Items)
            imgsList.remove(Integer.valueOf(i));

        gridImgs = Stream.concat(gp3Items.stream(), imgsList.subList(0, 17).stream()).collect(Collectors.toList());
        randGridImgs = getRandomImgsArray();


        gridViewGP3_login = (GridView) findViewById(R.id.gridViewGP3_login);
        CustomAdapter customAdapter = new CustomAdapter(randGridImgs, this);
        gridViewGP3_login.setAdapter(customAdapter);

        rl_gp3 = (RelativeLayout) findViewById(R.id.rl_gp3);
        //Log.v("rl childs", ""+ rl_gp3.getChildCount());
        //Log.v("gv childs", "" + gridViewGP3_login.getCount());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.v("width of screen", ""+ width);

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
                    if (myRec.contains((int) event.getRawX(), (int) event.getRawY()) && tempID !=randGridImgs.get(i) && gp3Items.contains(randGridImgs.get(i))) {
                        //Log.v("h", "" + randGridImgs.get(i));
                        if(touchedImages.size() == 0){
                            touchedImages.add(randGridImgs.get(i));
                            tempID = randGridImgs.get(i);
                        }
                        else{
                            if((!touchedImages.contains(gp3Items.get(addedToArray)) || !touchedImages.contains(gp3Items.get(gp3Items.size()-1))) && Integer.toString(touchedImages.get(addedToArray)).equals(Integer.toString(tempID))){
                                if(gp3Items.get(addedToArray) == randGridImgs.get(i) || gp3Items.get(gp3Items.size()-1) == randGridImgs.get(i)){
                                    if(randGridImgs.get(i) == gp3Items.get(gp3Items.size()-1) && touchedImages.size() == 6 ){
                                        Log.v("image", "" + randGridImgs.get(i));
                                        touchedImages.add(randGridImgs.get(i));
                                        addedToArray++;
                                        tempID = randGridImgs.get(i);
                                    }
                                    else if (touchedImages.size() < 6 && randGridImgs.get(i) != gp3Items.get(gp3Items.size()-1)){
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
                    if(isValidPW(touchedImages.get(0), touchedImages.get(touchedImages.size()-1), touchedImages))
                        intent = new Intent(ActivityGP_3_LogInFinal1.this, ActivityGP_3_LogInFinalSuccess.class);
                    else
                        intent = new Intent(ActivityGP_3_LogInFinal1.this, ActivityGP_3_LogInFinalFailure.class);
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
            if (start != gp3Items.get(gp3Items.size() - 2) || finish != gp3Items.get(gp3Items.size() - 1)) {
                isCorrect = false;
            }
            touchedImages.remove(0);
            touchedImages.remove(touchedImages.size() - 1);
            for (int i = 0; i < touchedImages.size(); i++) {
                if (!Integer.toString(touchedImages.get(i)).equals(Integer.toString(gp3Items.get(i)))) {
                    Log.v("touched", "" + touchedImages.get(i));
                    Log.v("Gp3", "" + gp3Items.get(i));
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

            if(imgID == gp3Items.get(gp3Items.size()-1))
                imageView.setBackgroundResource(R.drawable.image_border_finish);

            if(imgID == gp3Items.get(gp3Items.size()-2))
                imageView.setBackgroundResource(R.drawable.image_border_start);

            return view;
        }
    }

    public List<Integer> getRandomImgsArray(){
        List<Integer> prePasswordImages = new ArrayList<Integer>();
        while (prePasswordImages.size() < 24){
            final int min = 0;
            final int max = gridImgs.size()-1;
            final int randomIndex = new Random().nextInt((max - min) + 1) + min;
            if (!prePasswordImages.contains(gridImgs.get(randomIndex))){
                prePasswordImages.add(gridImgs.get(randomIndex));
            }
        }
        return prePasswordImages;
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