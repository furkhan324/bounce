package com.example.mohammed.bounce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * Created by mohammed on 5/17/15.
 */
public class Splash extends Activity{
    private ProgressBar progressBar;
    private int progressStatus = 0;

    private Handler handler = new Handler();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TextView tv=(TextView) findViewById(R.id.textView);
        TextView logo=(TextView) findViewById(R.id.logo1);
        Typeface generica2=Typeface.createFromAsset(getAssets(),"fonts/GenericaBold.otf");
        Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        logo.setTypeface(generica2);
        tv.setTypeface(generica);
      //  progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Start long running operation in a background thread

        new Thread(new Runnable() {
            public void run() {



                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 Intent i= new Intent("LOGIN");
                startActivity(i);
            }
        }).start();
    }
protected void onPause(){

    super.onPause();
    finish();
}


}
