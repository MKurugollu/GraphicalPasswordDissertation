package com.example.mustafakurugollumasters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ActivityGP_2_SignUpPassword extends AppCompatActivity {

    private GridView gridView;
    private Button nextButton;
    private boolean itemsLocked = false;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_p_2__sign_up_password);

        //Get Username
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            username = extras.getString("username");
        }

        nextButton = (Button) findViewById(R.id.gp2SignUpNext);

        //places images on gridView
        TypedArray imgs = getResources().obtainTypedArray(R.array.images);
        gridView = findViewById(R.id.gridViewgp2_signup);
        CustomAdapter customAdapter = new CustomAdapter(imgs, this);
        gridView.setAdapter(customAdapter);
        ArrayList<Integer> selectedImages = new ArrayList<Integer>();
        //when image is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(itemsLocked) return;
                Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                View imageView = view;

                if(selectedImages.size() < 5){
                    if(imageView.getBackground()==null) {
                        imageView.setBackground(highlight);
                        selectedImages.add(position);
                        if (selectedImages.size()==5){
                            nextButton.setEnabled(true);
                            itemsLocked = true;
                        }
                    }
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, ActivityGP_1_SignUpFinal.class);
                Intent intent = new Intent(ActivityGP_2_SignUpPassword.this, ActivityGP_2_SignUpFinal1.class);
                Bundle extras = new Bundle();
                extras.putString("username", username);
                extras.putIntegerArrayList("selectedImages", selectedImages);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    public class CustomAdapter extends BaseAdapter {
        private TypedArray imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(TypedArray imagesPhoto, Context context) {
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length();
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
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);

            }

            ImageView imageView = view.findViewById(R.id.imageView);

            imageView.setImageResource(imagesPhoto.getResourceId(i, 0));


            return view;
        }
    }

}