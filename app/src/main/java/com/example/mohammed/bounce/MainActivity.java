
package com.example.mohammed.bounce;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    private static String sUserId;
    private static final int MAX_CHAT_MESSAGES_TO_SHOW = 50;
    private ListView lvChat;
    private ArrayList<Message> mMessages;
    private ChatListAdapter mAdapter;
    private Handler handler = new Handler();
    public static final String USER_ID_KEY = "userId";

    private EditText etMessage;
    private Button btSend;
    private TextView logo;
    private TextView signup;
    private TextView fb;
    private TextView twit;
    private EditText usernameField;
    private EditText passwordField;
    private ImageView loginButton;
    private ImageView signUpButton;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        logo=(TextView) findViewById(R.id.logo2);
        usernameField=(EditText) findViewById(R.id.editText);
        passwordField=(EditText) findViewById(R.id.editText2);
        TextView pw2=(TextView) findViewById(R.id.textView);
        fb=(TextView) findViewById(R.id.textView2);
        twit=(TextView) findViewById(R.id.textView3);









        //sets typeface for logo
        Typeface generica=Typeface.createFromAsset(getAssets(),"fonts/Generica.otf");
        Typeface generica2=Typeface.createFromAsset(getAssets(),"fonts/GenericaBold.otf");
        logo.setTypeface(generica2);
        pw2.setTypeface(generica);
        usernameField.setTypeface(generica);
        passwordField.setTypeface(generica);
        fb.setTypeface(generica);
        twit.setTypeface(generica);


        loginButton = (ImageView) findViewById(R.id.imageView4);
        signUpButton = (ImageView) findViewById(R.id.imageView5);


        ////////////////////////////login and signup////////////////////////////////////////////////

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            Intent i= new Intent("HOME2");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),
                                    "Logged in Succesfully."
                                    , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error logging in.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //start sinch service
                            //start next activity

                            Intent i= new Intent("HOME2");
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),
                                    "Logged in Succesfully."
                                    , Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent i= new Intent("HOME2");
            startActivity(i);
            Toast.makeText(getApplicationContext(),
                    "Logged in Succesfully."
                    , Toast.LENGTH_LONG).show();

        }

        // User login
        /*
        if (ParseUser.getCurrentUser() != null) { // start with existing user
            startWithCurrentUser();
        } else { // If not logged in, login as a new anonymous user
            //in each case this will run
            //will basically create an anonymous user
            login();
        }*/
    }
    protected void onPause(){
        super.onPause();
        finish();

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshMessages();
            handler.postDelayed(this, 100);
        }
    };
    private void refreshMessages() {
        receiveMessage();
    }

    // Get the userId from the cached currentUser object
    private void startWithCurrentUser() {
        sUserId = ParseUser.getCurrentUser().getObjectId();
        setupMessagePosting();
    }

    // Setup button event handler which posts the entered message to Parse
    private void setupMessagePosting() {

    }

    // Query messages from Parse so we can load them into the chat adapter
    private void receiveMessage() {
        // Construct query to execute
        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);
        // Configure limit and sort order
        query.setLimit(MAX_CHAT_MESSAGES_TO_SHOW);
        query.orderByAscending("createdAt");
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground(new FindCallback<Message>() {
            public void done(List<Message> messages, ParseException e) {
                if (e == null) {
                    mMessages.clear();
                    mMessages.addAll(messages);
                    mAdapter.notifyDataSetChanged(); // update adapter
                    lvChat.invalidate(); // redraw listview
                } else {
                    Log.d("message", "Error: " + e.getMessage());
                }
            }
        });
    }





    // Create an anonymous user using ParseAnonymousUtils and set sUserId
    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.d(TAG, "Anonymous login failed: " + e.toString());
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }
}