package com.project.aidoor.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.project.aidoor.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;


public class HistoryActivity extends AppCompatActivity implements View.OnClickListener, HistoryViewListener
{

    private ArrayList<HistoryItem> historyItems = null;
    private HistoryAdapter historyAdapter = null;
//    private String username = null;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
//        initView();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        addChildEvent();
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

//        username = "user_"+new Random().nextInt(1000);
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

    private void addChildEvent()
    {
        databaseReference.child("History").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("sueyeon","addChileEvent in");
                HistoryItem item = dataSnapshot.getValue(HistoryItem.class);

                historyItems.add(item);
                historyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}