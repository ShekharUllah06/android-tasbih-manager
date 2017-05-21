package com.dev_station.tasbihmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dev_station.tasbihmanager.R;
import com.dev_station.tasbihmanager.adapter.TasbihItemListAdapter;
import com.dev_station.tasbihmanager.database.Database;
import com.dev_station.tasbihmanager.model.TasbihItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Test: ", "This is test");
        Log.d("Name: ", "Log d using");

        Database.init(MainActivity.this);


        final List<TasbihItem> allTasbihItems = Database.getAll();


        TasbihItemListAdapter tasbihItemListAdapter = new TasbihItemListAdapter(this,allTasbihItems);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(tasbihItemListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, EditTasbihItemActivity.class);
                //String name = nameArray[position];
                String name=allTasbihItems.get(position).getItemName();
                intent.putExtra("Name of Allah", name);
                int reciteTotal = allTasbihItems.get(position).getTotal();;
                intent.putExtra("total", reciteTotal);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
