package com.project.aidoor.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.project.aidoor.R;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity implements View.OnClickListener, HistoryViewListener
{

    private ArrayList<HistoryItem> historyItems = null;
    private HistoryAdapter historyAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
//        initView();
    }


    @Override
    public void onItemClick(int position, View view) {

    }


    @Override
    public void onClick(View view)
    {
//        switch (view.getId())
//        {
//            case R.id.historyPlayBtn:
//                //action when item's clicked (historyPlayBtn)
////                playVideo();
//                break;
//        }
    }


    private void init()
    {
        historyItems = new ArrayList<>();
    }


    private void initView()
    {
//        ImageButton playBtn = (ImageButton) findViewById(R.id.historyPlayBtn);
//
//        playBtn.setOnClickListener(this);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.historyList);
//        historyAdapter = new HistoryAdapter(historyItems, this, this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(historyAdapter);
    }
}