package com.assignment.minhlk.clevelandtour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc;
    RcAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = (RecyclerView)findViewById(R.id.rc);
        ArrayList<Location> arr = new ArrayList<>();
        arr.add(new Location("Cleveland Tower City","41.497065","-81.693920","http://www.towercitycenter.com/"));
        arr.add(new Location("Browns Football Field","41.506053","-81.699591","http://www.stadiumsofprofootball.com/stadiums/firstenergy-stadium/"));
        arr.add(new Location("Cleveland State University","41.502352","-81.675143","http://www.csuohio.edu/"));
        arr.add(new Location("Playhouse Square","41.501148","-81.680597","http://www.playhousesquare.org/"));
        arr.add(new Location("art museum","41.508887","-81.611571","http://www.clevelandart.org/"));
        adapter = new RcAdapter(arr,this);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);


    }
}
