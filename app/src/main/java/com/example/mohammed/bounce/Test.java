package com.example.mohammed.bounce;

import android.app.Activity;
import android.content.Intent;
//import android.net.ParseException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

//import java.text.ParseException;
//import org.apache.http.ParseException;

import java.util.List;
//import java.util.logging.Handler;

/**
 * Created by mohammed on 5/27/15.
 */
public class Test extends ActionBarActivity{
    private List<ParseObject> list;
    private TextView tv;
    private EditText ed;
    private Button button;
    private String talkedTo;
    private String currentUser;
    private String talkingTo;
    private ParseQuery<ParseObject> query;
    private ParseQuery<ParseObject> query2;
    private Handler handler = new Handler();
    private ParseObject bounce;
    private ParseObject bounce2;

    TextView logo;
    EditText editText3;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;
    TextView tv12;
    Toolbar toolbar;
    ImageView im;
    int color;
    private Long timeNow;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Runnable runnable = new Runnable() {
        //all optimized and well
        @Override
        public void run() {



                            bounce.put("text", ed.getText().toString());

                           try{
                                bounce.saveInBackground();

                               query2.getFirstInBackground(new GetCallback<ParseObject>() {
                                   public void done(ParseObject object, ParseException e) {
                                       if (object == null) {

                                       } else {
                                           bounce2=object;

                                       }
                                   }
                               });
                           } catch (Exception e) {

                               e.printStackTrace();
                           }

            tv.setText(bounce2.getString("text") + "  ");
            handler.postDelayed(this, 200);
        }
    };
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onCreate(Bundle savedInstanceState) {


        //////////////////////////////////////////////passes and assigns info from last activity to this one on who the user is and who he is talking to///////////
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("userId");
            talkingTo=extras.getString("talkingTo");
            talkedTo=extras.getString("userName");
            color=extras.getInt("color");
            if(color==R.drawable.purpleicon2){
                setTheme(R.style.Purple);
            }
            else if(color==R.drawable.orangeicon||color==R.drawable.redicon2){
                setTheme(R.style.Orange);
            }
            else if(color==R.drawable.blue3){
                setTheme(R.style.ActionBarPopupThemeOverlay);
            }

        }
        else{


        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ImageView im12 =(ImageView) findViewById(R.id.imageView12);
        ImageView im23 =(ImageView) findViewById(R.id.imageView23);
        ImageView im25 =(ImageView) findViewById(R.id.imageView25);
        ImageView im21 =(ImageView) findViewById(R.id.imageView21);


        if(color==R.drawable.purpleicon2){
            im12.setImageResource(R.drawable.indigobar);
            im23.setImageResource(R.drawable.indigoicon);
            im25.setImageResource(R.drawable.purpleicon2);
            im21.setImageResource(R.drawable.purpleicon2);
        }
        else if(color==R.drawable.orangeicon||color==R.drawable.redicon2){
            im12.setImageResource(R.drawable.orangebar);
            im23.setImageResource(R.drawable.orangeicon2);
            im25.setImageResource(R.drawable.orangeicon);
            im21.setImageResource(R.drawable.orangeicon);
        }
        else if(color==R.drawable.blue3){
            im12.setImageResource(R.drawable.bluebar);
            im23.setImageResource(R.drawable.lightblueicon);
            im25.setImageResource(R.drawable.blue3);
            im21.setImageResource(R.drawable.blue3);
        }
        //more gui synchromization

        //creating a parse object
        // only create this object if it exists;
        //can be implemented with simple if test with query
        logo =(TextView) findViewById(R.id.textView5);


        tv8 =(TextView) findViewById(R.id.textView8);

        //tv10 =(TextView) findViewById(R.id.textView10);
        tv11 =(TextView) findViewById(R.id.textView11);
        tv12 =(TextView) findViewById(R.id.textView12);
        TextView tv13 =(TextView) findViewById(R.id.textView13);

        TextView tv16 =(TextView) findViewById(R.id.textView16);

        ed =(EditText) findViewById(R.id.editText3);
        tv =(EditText) findViewById(R.id.editText4);



        Typeface generica2=Typeface.createFromAsset(getAssets(), "fonts/GenericaBold.otf");
        Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        Typeface book=Typeface.createFromAsset(getAssets(), "fonts/Avenir-Book.otf");
        Typeface ob=Typeface.createFromAsset(getAssets(), "fonts/Avenir-BookOblique.otf");
        Typeface black=Typeface.createFromAsset(getAssets(), "fonts/Avenir-Black.otf");
        logo.setTypeface(ob);


        tv8.setTypeface(ob);

      //  tv10.setTypeface(ob);
        tv11.setTypeface(black);
        tv12.setTypeface(black);
        tv13.setTypeface(black);

        tv16.setTypeface(generica);

        ed.setTypeface(book);
        tv.setTypeface(book);
        TextView tv20 =(TextView) findViewById(R.id.textView20);
        TextView tv10=(TextView) findViewById(R.id.textView10);
        TextView tv17 =(TextView) findViewById(R.id.textView17);
        TextView tv15=(TextView) findViewById(R.id.textView15);
        TextView tv18 =(TextView) findViewById(R.id.textView18);
        TextView tv19=(TextView) findViewById(R.id.textView19);

        tv10.setText(talkedTo);
        tv10.setTypeface(black);

        tv20.setTypeface(ob);
        tv18.setTypeface(ob);
        tv19.setTypeface(ob);
        tv17.setTypeface(black);
        tv15.setTypeface(black);


        //////////////////////////////////////////////////////////////////////////////////
        im =(ImageView) findViewById(R.id.imageView16);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed.setText("");


            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        query = ParseQuery.getQuery("Bounce");
        query.whereEqualTo("currentUserId", currentUser);
        query.whereEqualTo("talkingTo", talkingTo);

        try{bounce=query.getFirst();
            }
        catch(ParseException e){

            ParseObject po = new ParseObject("Bounce");
            po.put("text", "whats on the test");//we would like to get this data from the editText
            po.put("currentUserId", currentUser);
            po.put("talkingTo", talkingTo);
            po.put("seen", false);//as its written
            po.saveInBackground();
            bounce = po;
        }
            ed.setText(bounce.getString("text"));
            query2 = ParseQuery.getQuery("Bounce");
            query2.whereEqualTo("currentUserId", talkingTo);
            query2.whereEqualTo("talkingTo", currentUser);
        try{
            bounce2=query2.getFirst();}

        catch(ParseException e){

                ParseObject po = new ParseObject("Bounce");
                po.put("text", "whats on the test2");//we would like to get this data from the editText
                po.put("currentUserId", talkingTo);
                po.put("talkingTo", currentUser);
                po.put("seen", false);//as its written
                po.saveInBackground();
                bounce2 = po;
            }
            tv.setText(bounce2.getString("text"));


        handler.postDelayed(runnable, 200);

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

















































}

