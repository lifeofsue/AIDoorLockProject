package com.project.aidoor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");

        final String[] mid = {"히어로즈", "24시", "로스트"};

        ListView list = (ListView) findViewById(R.id.historyList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mid);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>  arg0, View arg1, int arg2, long arg3){
                Toast.makeText(getApplicationContext(), mid[arg2], Toast.LENGTH_SHORT).show();
            }
        });
    }
}