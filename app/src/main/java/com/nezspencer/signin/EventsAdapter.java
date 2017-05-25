package com.nezspencer.signin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nezspencer on 5/25/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.Holder> {

    private ArrayList<EventPojo> events;
    private Context context;
    public EventsAdapter(Context context, ArrayList<EventPojo> list) {
        this.events = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event_item,
                parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        EventPojo pojo = events.get(position);
        holder.titleText.setText(pojo.getTitle());
        holder.summaryText.setText(pojo.getSummary());
        holder.dateText.setText(pojo.getDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private TextView titleText;
        private TextView summaryText;
        private TextView dateText;

        public Holder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.tv_title);
            summaryText = (TextView) itemView.findViewById(R.id.tv_summary);
            dateText = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
