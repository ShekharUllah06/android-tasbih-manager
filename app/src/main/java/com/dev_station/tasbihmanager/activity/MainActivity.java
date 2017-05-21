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
import com.dev_station.tasbihmanager.database.DatabaseHandler;
import com.dev_station.tasbihmanager.model.TasbihItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] nameArray = {"Allah","Ar-Rahman","Ar-Raheem","Al-Malik","Al-Quddus","As-Salaam" };

    int total=0;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addTasbihItem(new TasbihItem("Subhan Allah",0));
        db.addTasbihItem(new TasbihItem("Subhan Allah1",0));
        db.addTasbihItem(new TasbihItem("Subhan Allah1",0));
        db.addTasbihItem(new TasbihItem("Alhamdulillah",0));
        db.addTasbihItem(new TasbihItem("Allahu Akbar",0));
        List<TasbihItem> contacts = db.getAllContacts();

        int i=0;

        for (TasbihItem cn : contacts) {

            String n=cn.getItemName();
            String log = "Item Name: "+n+" ,Total: " + cn.getTotal();
            nameArray[i]=n;
            i=i+1;
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        TasbihItemListAdapter tasbihItemListAdapter = new TasbihItemListAdapter(this, nameArray, total);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(tasbihItemListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, EditTasbihItemActivity.class);
                //String name = nameArray[position];
                String name=parent.getItemAtPosition(position).toString();
                intent.putExtra("Name of Allah", name);
                int reciteTotal = total;
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
