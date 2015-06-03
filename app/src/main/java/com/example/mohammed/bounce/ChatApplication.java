package com.example.mohammed.bounce;



import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mohammed on 5/14/15.
 */
public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Message.class);
        Parse.initialize(this, "mZP4W865O894Phw4YmGuQsOzbnM9kEhYUg38AL3I", "LwHBQAqMyYV7qASGCohkBmCBSbf1N5oFPSZCb4Zb");
    }
}