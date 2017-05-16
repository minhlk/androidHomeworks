package com.assignment.minhlk.tacopronto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    List<String> mListChecked = new ArrayList<>();
    Button btnCommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkPermiss();

    }

    private void init() {


        btnCommit = (Button) findViewById(R.id.btnCommit);
        btnCommit.setOnClickListener(this);
        ((CheckBox) findViewById(R.id.cb1)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb2)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb3)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb4)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb5)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb6)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb7)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb8)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb9)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb10)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb11)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb12)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb13)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.cb14)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.size1)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.size2)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.torialla1)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.torialla2)).setOnCheckedChangeListener(this);
        mListChecked.add(((RadioButton) findViewById(R.id.size1)).getText().toString());
        mListChecked.add(((RadioButton) findViewById(R.id.torialla1)).getText().toString());

    }

    private void checkPermiss() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override
    public void onClick(View v) {
        String message = "";
        String num = "5556";
        for(int i =0 ; i< mListChecked.size(); i++){
             message += mListChecked.get(i)+ " ";

        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(num, null, message, null, null);
        Toast.makeText(this, "Message was sent :" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            mListChecked.add(buttonView.getText().toString());
        else
            mListChecked.remove(buttonView.getText().toString());
    }
}
