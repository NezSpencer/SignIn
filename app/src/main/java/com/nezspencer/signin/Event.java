package com.nezspencer.signin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        if(!RegisterAds.NAME.isEmpty())
            ((TextView)findViewById(R.id.user_name)).setText(RegisterAds.NAME);

    }
}
