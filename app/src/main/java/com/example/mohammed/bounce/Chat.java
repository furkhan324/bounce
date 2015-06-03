package com.example.mohammed.bounce;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by mohammed on 5/20/15.
 */
public class Chat extends ActionBarActivity{
TextView logo;
    EditText editText3;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;
    TextView tv12;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        Bundle extras = getIntent().getExtras();
        String value ="";
        if (extras != null) {
            value = extras.getString("USERNAME");
        }
        else{


        }

        setContentView(R.layout.activity_chat);

        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
// enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setTintColor(getResources().getColor(R.color.ColorPrimaryDark));

        logo =(TextView) findViewById(R.id.textView5);


        tv8 =(TextView) findViewById(R.id.textView8);
        //tv9 =(TextView) findViewById(R.id.textView9);
        tv9.setText(value);
        //tv10 =(TextView) findViewById(R.id.textView10);
        tv11 =(TextView) findViewById(R.id.textView11);
        tv12 =(TextView) findViewById(R.id.textView12);
        TextView tv13 =(TextView) findViewById(R.id.textView13);

        TextView tv16 =(TextView) findViewById(R.id.textView16);

        EditText ed3 =(EditText) findViewById(R.id.editText3);
        EditText ed4 =(EditText) findViewById(R.id.editText4);

        Typeface generica2=Typeface.createFromAsset(getAssets(), "fonts/GenericaBold.otf");
        Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        Typeface book=Typeface.createFromAsset(getAssets(), "fonts/Avenir-Book.otf");
        Typeface ob=Typeface.createFromAsset(getAssets(), "fonts/Avenir-BookOblique.otf");
        Typeface black=Typeface.createFromAsset(getAssets(), "fonts/Avenir-Black.otf");
        logo.setTypeface(ob);


        tv8.setTypeface(ob);
        //tv9.setTypeface(black);
        //tv10.setTypeface(ob);
        tv11.setTypeface(book);
        tv12.setTypeface(book);
        tv13.setTypeface(ob);

        tv16.setTypeface(generica);

        ed3.setTypeface(book);
        ed4.setTypeface(book);
    }



}
