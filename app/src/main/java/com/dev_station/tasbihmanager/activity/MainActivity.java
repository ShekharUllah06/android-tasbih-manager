package com.dev_station.tasbihmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import com.dev_station.tasbihmanager.R;
import com.dev_station.tasbihmanager.adapter.TasbihItemListAdapter;

public class MainActivity extends AppCompatActivity {

    String[] nameArray = {"Allah","Ar-Rahman","Ar-Raheem","Al-Malik","Al-Quddus","As-Salaam" };

    int total=0;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TasbihItemListAdapter whatever = new TasbihItemListAdapter(this, nameArray, total);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(whatever);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
