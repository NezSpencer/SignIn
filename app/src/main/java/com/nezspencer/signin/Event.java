package com.nezspencer.signin;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class Event extends AppCompatActivity {

    private RecyclerView eventRecycler;
    public static ArrayList<EventPojo> eventList;
    private FloatingActionButton fabAdd;
    private static EventsAdapter adapter;
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

        final Dialog dialog;
        TextView e_name;
        final EditText e_title;
        final EditText e_date;
        final EditText e_time;
        Button send;
        dialog = new Dialog(Event.this);
        dialog.setContentView(R.layout.event_dialog);
            dialog.setCancelable(false);
            e_name =(TextView) dialog.findViewById(R.id.name);
            e_title =(EditText) dialog.findViewById(R.id.title);
            e_date =(EditText) dialog.findViewById(R.id.date);
            e_time =(EditText) dialog.findViewById(R.id.time);
            send =(Button) dialog.findViewById(R.id.sendd);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventPojo pojo = new EventPojo(e_title.getText().toString(),
                            e_date.getText().toString(), e_time.getText().toString());
                    addToList(pojo);
                    dialog.dismiss();


                }
            });
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }

        public static void addToList(EventPojo pojo){

            eventList.add(pojo);
            adapter.notifyDataSetChanged();

        }
    }

