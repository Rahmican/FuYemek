package com.app.rahmican.fuyemek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Rahmican on 30.11.2016.
 */

public class splashScreen extends Activity {
    ImageView Image;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Image = (ImageView) findViewById(R.id.imageView);


        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    finish();
                }
            }
        };
        timerThread.start();



    }

}
