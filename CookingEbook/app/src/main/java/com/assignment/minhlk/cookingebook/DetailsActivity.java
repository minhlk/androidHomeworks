package com.assignment.minhlk.cookingebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Integer[] imgs = new Integer[10];
        imgs[0] = R.drawable.piconebig;
        imgs[1] = R.drawable.pictwobig;
        imgs[2] = R.drawable.picthreebig;
        imgs[3] = R.drawable.picfourthbig;
        imgs[4] = R.drawable.picfifthbig;

        imgs[5] = R.drawable.piconebig;
        imgs[6] = R.drawable.pictwobig;
        imgs[7] = R.drawable.picthreebig;
        imgs[8] = R.drawable.picfourthbig;
        imgs[9] = R.drawable.picfifthbig;



        Bundle bd = getIntent().getExtras();
        int position = bd.getInt("text",-1);
        if (position != -1) {
//            Toast.makeText(this, "details activity" + position, Toast.LENGTH_SHORT).show();

            ((TextView) findViewById(R.id.ttest)).setText(Html.fromHtml(getResources().getStringArray(R.array.cooks_recipe)[position]));
        }
        else{
            position = bd.getInt("image",-1);
            ((ImageView) findViewById(R.id.imgBig)).setImageDrawable(getResources().getDrawable(imgs[position]));
        }



    }
}
