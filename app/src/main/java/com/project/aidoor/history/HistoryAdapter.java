package com.project.aidoor.history;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.project.aidoor.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>
{
    private Context context = null;
    private ArrayList<HistoryItem> historyItems = null;
    private HistoryViewListener historyViewListener = null;

    public HistoryAdapter(ArrayList<HistoryItem> items, Context context, HistoryViewListener listener)
    {
        this.historyItems = items;
        this.context = context;
        this.historyViewListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(viewHolder.itemView).load(historyItems.get(i).getProfile()).into(viewHolder.profileView);
        viewHolder.timeView.setText(historyItems.get(i).getTime());
        viewHolder.statusView.setText(historyItems.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView profileView = null;
        public TextView timeView = null;
        public TextView statusView = null;

        public ViewHolder(View view){
            super(view);
            profileView = (ImageView) view.findViewById(R.id.historyProfile);
            timeView = (TextView) view.findViewById(R.id.historyTime);
            statusView = (TextView) view.findViewById(R.id.historyStatus);
        }

        @Override
        public void onClick(View view)
        {
            historyViewListener.onItemClick(getAdapterPosition(), view);
        }
    }
}