package com.nezspencer.signin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nezspencer on 5/25/17.
 */

public class EventsAdapter extends RecyclerView.Adapter {

    private ArrayList<EventPojo> events;
    private Context context;
    public EventsAdapter(Context context, ArrayList<EventPojo> list) {
        this.events = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
