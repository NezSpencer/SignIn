package com.nezspencer.signin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Event extends AppCompatActivity {

    private RecyclerView eventRecycler;
    public static ArrayList<EventPojo> eventList;
    private FloatingActionButton fabAdd;
    private EventsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventList = new ArrayList<>();
        adapter = new EventsAdapter(this, eventList);
        eventRecycler = (RecyclerView) findViewById(R.id.rv_event);
        eventRecycler.setAdapter(adapter);

        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent();
            }
        });
    }

    private void addEvent() {
        // call dialogFragment here


    }


}
