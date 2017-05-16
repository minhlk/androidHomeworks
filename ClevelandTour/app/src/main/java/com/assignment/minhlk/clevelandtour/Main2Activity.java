package com.assignment.minhlk.clevelandtour;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    String lat,log,url,title;
    Button bMap,bUrl;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bMap = (Button) findViewById(R.id.bMap);
        bUrl = (Button) findViewById(R.id.bUrl);
        t = (TextView) findViewById(R.id.t);
        Bundle bd = getIntent().getBundleExtra("bd");
        title = bd.getString("title");
        lat = bd.getString("lat");
        log = bd.getString("log");
        url = bd.getString("url");
        t.setText(title);
        bMap.setOnClickListener(this);
        bUrl.setOnClickListener(this);
        
        

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bMap:
                Bundle bd = new Bundle();
                bd.putString("title",title);
                bd.putString("lat",lat);
                bd.putString("log",log);
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("bd",bd);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);

                break;
            case R.id.bUrl:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            
            
        }
    }
}
