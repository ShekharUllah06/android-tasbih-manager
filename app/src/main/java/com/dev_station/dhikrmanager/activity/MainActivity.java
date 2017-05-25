package com.dev_station.dhikrmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.dev_station.dhikrmanager.R;
import com.dev_station.dhikrmanager.adapter.DhikirTypeListAdapter;
import com.dev_station.dhikrmanager.database.Database;
import com.dev_station.dhikrmanager.model.DhikrType;

public class MainActivity extends BaseActivity {

    ListView listView;
    String[] dhikrTypeArray = {
            DhikrType.NAME_OF_ALLAH.toString(),
            DhikrType.TASBIH.toString(),
            DhikrType.TAHMID.toString(),
            DhikrType.TAKBIR.toString(),
            DhikrType.TAHLIL.toString(),
            DhikrType.ISTIGFAR.toString(),
            DhikrType.DUROOD.toString(),
            DhikrType.SURAH.toString(),
            DhikrType.DUA.toString(),
            DhikrType.OTHERS.toString()};
    Integer[] totalItemArray=new Integer[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Test: ", "This is test");
        Log.d("Name: ", "Log d using");

        Database.init(MainActivity.this);
        for(int i=0;i<dhikrTypeArray.length;i++){
            totalItemArray[i]=Database.getTotalRow(dhikrTypeArray[i]);
        }

        DhikirTypeListAdapter dhikrTypeListAdapter = new DhikirTypeListAdapter(this,dhikrTypeArray,totalItemArray);
        listView = (ListView) findViewById(R.id.tasbihListView);
        listView.setAdapter(dhikrTypeListAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                //String name = nameArray[position];
                String name=dhikrTypeArray[position];
                intent.putExtra("DhikirType", name);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.menu_add).setVisible(false);
        return true;
    }


}
