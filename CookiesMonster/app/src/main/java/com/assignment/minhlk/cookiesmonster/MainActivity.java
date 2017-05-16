package com.assignment.minhlk.cookiesmonster;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressBar pb1,pb2,pb3,pbc1;
    Button b1,b2;
    Handler mHandler = new Handler();
    TextView t4,t1,t2,t3,tResult;
    Boolean isRunning = false;
    public int time
    ,cookies
    ,monsterfeed1
    ,monsterfeed2
    ,monstereat1
    ,monstereat2
    ;
    Timer mTimer = new Timer();
    Thread thread1,thread2,threadGrandma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        pb3 = (ProgressBar) findViewById(R.id.pb3);
        pbc1 = (ProgressBar) findViewById(R.id.pbc1);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        t4 = (TextView) findViewById(R.id.t4);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        tResult = (TextView) findViewById(R.id.tResult);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);



    }
    private void createThread1(){
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monstereat1 < 100 && !thread1.isInterrupted()) {
                    if (!isRunning) {
                        isRunning = true;
                        Random r = new Random();
                        monsterfeed1 = r.nextInt(cookies);
                        cookies -= monsterfeed1;
                        monstereat1 += monsterfeed1;


                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                t1.setText("Total cookies eaten so far "+monstereat1);
                                pb1.setProgress(monstereat1);
                                t3.setText("Total cookies baked so far "+cookies);
                            }
                        });
                        SystemClock.sleep(r.nextInt(5)*1000);
                        isRunning = false;
                    }
                }
                onBreak();

            }
        });
    }
    private void createThreadGrandma(){
        threadGrandma = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monstereat1 < 100 && monstereat2 < 100  && !threadGrandma.isInterrupted()) {

                    Random r = new Random();
                    cookies +=  r.nextInt(10);




                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
//                                Toast.makeText(MainActivity.this, cookies +" : cookies", Toast.LENGTH_SHORT).show();
                            t3.setText("Total cookies baked so far "+cookies);
                        }
                    });
                    SystemClock.sleep(5000);

                }


            }
        });

    }
    private void createThread2(){
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while( monstereat2 < 100 && !thread2.isInterrupted()) {
                    if (!isRunning) {
                        isRunning = true;
                        Random r = new Random();
                        monsterfeed2 = r.nextInt(cookies);
                        cookies -= monsterfeed2;
                        monstereat2 += monsterfeed2;


                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                t2.setText("Total cookies eaten so far "+monstereat2);
                                pb2.setProgress(monstereat2);
                                t3.setText("Total cookies baked so far "+cookies);
                            }
                        });
                        SystemClock.sleep(r.nextInt(5)*1000);
                        isRunning = false;
                    }
                }
                onBreak();


            }
        });
    }

    private void onBreak(){

        thread1.interrupt();
        thread2.interrupt();
        threadGrandma.interrupt();
        mTimer.cancel();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                        if(pbc1!= null) pbc1.setVisibility(View.INVISIBLE);
//                        Toast.makeText(MainActivity.this, "Game has stopped", Toast.LENGTH_SHORT).show();
                if(monstereat1 > monstereat2)
                    tResult.setText("The winner is monster 1");
                if(monstereat1 < monstereat2)
                    tResult.setText("The winner is monster 2");
            }
        });

    }
    private void create(){
        isRunning = false;
        mTimer = new Timer();
        if(pbc1!= null) pbc1.setVisibility(View.VISIBLE);
        pb1.setMax(100);
        pb2.setMax(100);
        pb1.setProgress(0);
        pb2.setProgress(0);
        tResult.setText("");
        time = 0;
        cookies = 10;
        monsterfeed1 = 0;
        monsterfeed2 = 0;
        monstereat1 = 0;
        monstereat2 = 0;
        createThread1();
        createThread2();
        createThreadGrandma();
        startTimer();
        thread1.start();
        thread2.start();
        threadGrandma.start();
        t1.setText("Total cookies eaten so far ");
        t2.setText("Total cookies eaten so far ");
        t3.setText("Total cookies baked so far ");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                if(thread1 !=null || thread2 !=null || threadGrandma != null)
                    onBreak();
                create();
                break;
            case R.id.b2:
               onBreak();
                break;

        }
    }

    private void startTimer() {

       
        pb3.setMax(120);
        TimerTask  mTimerTask = new TimerTask() {
            @Override
            public void run() {
                      mHandler.post(new Runnable() {
                          @Override
                          public void run() {
                                //update UI
                              time++;
                              t4.setText("Simulation clock: "+( time +1 )+"/120 sec");
                            pb3.setProgress(time);
                              if(time == 119){
                                  onBreak();
                              }

                          }
                      });
            }
        };
    mTimer.schedule(mTimerTask,500,1000);

    }
}
